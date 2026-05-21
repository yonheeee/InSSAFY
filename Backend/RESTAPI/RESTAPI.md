## REST API

- **약자로 자원을 이름으로 구분하여 해당 자원 상태를 주고 받는 모든 것을 의미**
- **Respresentational** : 자원의 표현으로 정보 상태를 JSON,XML 등의 형식으로 표현됨
- **State** : 자원의 상태
- **Transfer** : 네트워크를 통해 상태를 전송

#### REST

1. HTTP URI(Uniform Resource Indentifier)를 통해 자원을 명시
2. HTTP Method(POST, GET, PUT, DELETE, PATCH 등)을 통해
3. 해당 자원(URI)에 대한 CRUD Operation을 적용하는 것을 의미

---

### REST 아키텍처의 핵심 원칙

**① 클라이언트-서버 구조 (Client-Server Architecture)**

 서버와 클라이언트의 역할 명확하게 분리, 서버는 데이터 제공과 비즈니스 로직 담당, 클라이언트는 사용자 인증이나 UI에 집중해 의존성 줄어 독립적으로 발전

**② 무상태성 (Stateless)**

서버는 클라이언트 상태(State)를 기억하지 않음, 서버는 들어오는 요청만 보고 그대로 처리해, 클라이언트를 기억할 필요가 없으니 서버를 여러 대 가동해도 무리 없어 확장이 쉬워짐

**③ 캐시 가능성 (Cacheable)**

HTTP가 가진 캐싱을 사용할 수 있음, 대량의 데이터 요청 시 서버까지 가지 않고 브라우저나 중각 프록시 서버에서 데이터를 바로 꺼내올 수 있어 응답 속도 빨라짐

**④ 자체 표현 구조 (Self-descriptive)**

REST API메세지만 보고 요청이 무엇을 의미하고, 어떻게 처리하는지 직관적 이해 가능

| HTTP Method | CRUD 연산 | 핵심 역할                           | 안전(Safe)? | 멱등성(Idempotent)? |
| ----------- | --------- | ----------------------------------- | ----------- | ------------------- |
| **GET**     | Read      | 자원 조회                           | **Yes**     | **Yes**             |
| **POST**    | Create    | 새로운 자원 생성                    | No          | No                  |
| **PUT**     | Update    | 자원의 전체 수정 (또는 없으면 생성) | No          | **Yes**             |
| **PATCH**   | Update    | 자원의 일부 수정                    | No          | No (일반적으로)     |
| **DELETE**  | Delete    | 자원 삭제                           | No          | **Yes**             |

---

### REST API를 위한 Annotation

- `@ResponseBody`
    - 일반 `@Conreoller` 에서 REST 서비스를 위해 사용
    - 뷰를 연동하지 않고 데이터(문자열, JSON, XML)만 전송
- `@RequestBody`
    - body로 전송된 JSON 데이터를 객체로 변환
    - 파라미터를 통해 전달되는 데이터를 처리하는 `@ModelAttribute` 와 유사한 역할
    - 등록/수정할 상세 데이터 전
- `@RestController`
    - 기존 `@Controller` 는 String을 반환하면, 화면 파일을 브라우저에 뜨워줌
    - 화면을 찾지 않고 데이터를 HTTP Response Body에 넣어 전송
    - `@Controller` 이면서 `@ResponseBody`
    - 해당 Controller의 모든 `@RequestMapping` 메서드 반환은 View가 아닌 데이터
    - → 모든 요청 처리 메서드는 `@ResponseBody` 로 처리됨
- `@PathVariable`
    - URL 상의 변수를 처리하기 위한 annotation
    - `@RequestParam` 처럼 자동 형 변환 등 지원
    - 특정 자원의 식별 및 지정
- `ResponseEntity`
    - 클라이언트에게 보낼 HTTP 응답(Response)을 제어할 수 있도록 도와주는 객체
    - 3대 구성 요소
        1. **Status(HTTP 상태 코드)**  
        : 요청이 성공했는지(`200 OK`), 새로운 자원이 잘 생성되었는지(`201 Created`), 클라이언트 요청이 잘못되었는지(`400 Bad Request`), 서버에 에러가 났는지(`500 Internal Server Error`) 등
        2. **Headers(HTTP 헤더)** 
            
            : 응답 데이터의 타입(`Content-Type: application/json`)이나 캐시 설정 등 응답에 대한 메타데이터
            
        3. **Body (HTTP 바디)**
            
             **:**클라이언트가 실제로 필요로 하는 회원 정보, 게시글 내용 등의 데이터 객체(주로 JSON으로 변환됨)가 들어감

---

```java
@RestController
@RequestMapping("api/todos") // 1. 명사 중심의 공통 자원(Resource) 설정
public class RestTodoController{
		// 2. 인터페이스 타입을 바라보는 final 필드
		private final TodoService todoService;
		
		// 3. 생성자 주입
		// @Qualifier : 명확하게 지시하는 식별자 역할 
		public RestTodoController(@Qualifier("TodoServiceMapperImpl") TodoService todoService){
				this.todoService = todoService;
		}
		
		// [GET] 전체 할 일 목록 조회 (Read)
		@GetMapping
		public ResponseEntity<List<Todo>> getAllTodos(){
				List<Todo> todos = todoService.findAll();
				
				//성공 시 200 ok 상태 코드와 함께 데이터 반
				return ResponsseEntity.ok(todos);
		}	
		
		// [GET] 특정 할 일 상세 조회(Read)
		// @PathVariable로 URI 경로에 있는 {id} 값을 자바 변수로 캐치
		@GetMapping("/{id}")
		public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
				Todo todo = todoService.findById(id);
				
				if(todo == null){
						return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(todo);
		}
		
		// [POST] 새로운 할 일 등록(Create)
		// @RequestBody로 클라이언트가 보낸 JSON 데이터를 자바 객체로 변환
		@PostMappint
		public ResponseEntity<Todo> createTodo(@RequestBody TodoDto todoDto){
				Todo savedTodo = todoService.save(todoDto);
				// 자원 생성 성공 시 201 Created 상태 코드 반
				return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
		}
		
		
	
}
```