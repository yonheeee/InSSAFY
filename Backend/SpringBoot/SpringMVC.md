## Spring MVC

- MVC 기반의 Web Application을 작성하기 위한 Spring Framework의 하위모듈
- front controller pattern : DispatcherServlet
    - / 경로를 처리하므로 모든 요청을 받아드림
        - action parameter 방식이 아닌 URL 기반으로 요청 분기

---

### Spring@MVC 구성요소

#### DispatcherServlet

- Front Controller로 모든 요청의 진입점

```jsx
//SpringBoot가 자동으로 등록
//aplication.properties에서 경로만 바꿀 수 있음
spring.mvc.servlet.path=/api  //기본값은 /
```

#### HandlerMapping - URL 지도

- 요청 URL을 보고 어떤 Controller메서드가 처리할지 찾아줌
    - `@RequestMapping` : 기본적 매핑 어노테이션
        - 클래스 레벨과 메서드 레벨 모두 사용 가능
        - HTTP(GET, POST)를 직접 지정
        
        ```java
        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello(){
        	return "h1";
        }
        ```
        
    - `@GetMapping` : `@RequestMapping` 을 짧고 명확하게 쓰기 위함
        - method 속성을 일일이 적을 필요가 없음
        - 데이터 조회(GET) 전용
        
        ```java
        @GetMapping("/hello")
        public String hello(){
        	return "hi";
        }
        ```
        

#### HandlerAdapter - 실행 대리인

- HandlerMapping이 Controller을 찾았다면 HandlerAdapter은 실제로 실행
- **파라미터 바인딩**
- ModelAndView 형식으로 반환

```java
public interface HandlerAdapter {

    boolean supports(Object handler);

    ModelAndView handle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    );
}
```

#### Controller - 요청 처리

- `@RestController` 요청 처리 + DTO 반환
- 실제로 요청을 받아서 Service 호출하고 결과 반환
- 유일하게 개발자가 만드는 구

#### MessageConverter - 번역기

- DTO ↔ JSON 변환
    - Jackson 라이브러리

#### ViewResolver - HTML 렌더링(REST API에선 사용하지 않음)

- view 이름 → 실제 파일 찾기 `@Controller` 쓸 때만 동작

---

### Spring@MVC 흐름

![image.png](attachment:080b8755-b1df-4549-b795-e10e3ae3c702:image.png)

1. **Dispatcher Servlet이 요청을 받음**
    - 모든 HTTP 요청은 DispatcherServlet을 거침
2. **HandlerMapping이 어떤 Controller로 보낼지 찾음**
    - URL을 보고 “이 요청은 `UserController.getUser` 가 처리해야겠다”
3. **HandlerAdapter - Controller를 실행해줌**
    - 찾은 Controller 실제로 실행
4. **Controller → Service → Repository → DB**
    - 실제 비즈니스 로직 실행되고 결과 DTO 반환
5. **MessageConverter가 DTO를 JSON으로 변환**
    - `@RestController`가 붙어있으면 반환한 DTO를 자동으로 JSON으로 바꿔줌
        
        → 이게 `Jackson` 라이브러리가 하는 일