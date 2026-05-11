## Filter & Listener

## Filter

클라이언트 요청이 서블릿에 전달되기 전, 서블릿이 생성한 응답이 클라이언트에 전달되기 전에 특정 작업을 수행하는 객체

```java
Client
  ↓
Filter
  ↓
Servlet
  ↓
Controller / Service
  ↓
View
```

→ “**요청이 서블릿으로 가기 전에 가로 채서 공통 작업을 수행**”

---

### 1. 주요 역할

- **인증 및 권한 체크** : 로그인하지 않은 사용자가 특정 페이지에 접근 차단
- **인코딩 설정** : 모든 요청에 대해 UTF-8 같은 인코딩 일괄 적용
- **로깅(Logging)** : 어떤 IP가 어떤 페이지를 요청했는지 기록

---

### 2. 동작 구조

1. **Request 전처리** : 서블릿이 호출되기 전 요청 데이터 가공하거나 검사
2. **chain.doFilter() 호출** : 다음 필터로 요청을 넘기거나, 마지막 필터라면 서블릿 실행
3. **Response 후처리** : 서브릿 실행이 끝난 후 응답 데이터를 클라이언트에 보내기 전 수정하거나 기록

**filter chain(필터 체인) :** 여러 개의 Filter 순서대로 실행하는 연결 구조

```java
요청
 ↓
[Filter1] -> 인코딩 처리
 ↓
[Filter2] -> 로그인 체크
 ↓
[Filter3] -> 로그 기록
 ↓
Servlet
 ↓
응답
 ↑
[Filter3]
 ↑
[Filter2]
 ↑
[Filter1]
```

---

### 3. 주요 메서드

| **메서드** | **설명** |
| --- | --- |
| **`init()`** | 필터가 생성될 때 한 번 실행. 초기화 설정을 담당 |
| **`doFilter()`** | **핵심 메서드** 요청과 응답이 올 때마다 실행되며 필터링 로직을 구현 |
| **`destroy()`** | 필터 객체가 소멸될 때 호출. 자원 해제 등을 수행 |

---

### 4. 필터 등록 방법

**방법 1 :** `@WebFilter`  **어노테이션 사용**

```java
@WebFilter("/*") // 모든 요청에 대해 필터 적용
public class MyFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        // 전처리 로직
        chain.doFilter(request, response); // 다음 단계로 이동s
        // 후처리 로직
    }
}
```

**방법 2 : `web.xml` 설정**

`web.xml` 파일에 `<filter>`와 `<filter-mapping>` 태그를 사용하여 설정

<br><br>
## Listener

웹 애플리케이션에서 발생하는 이벤트에 대한 감지하고, 그에 따른 동작을 수행

→ **Listen는 요청 흐름에 끼어드는 게 아닌 “옆에서 감시”하는 느낌**

---

### 1. 동작 원리

1. **이벤트 발생** : ex) 웹 애플리케이션 시작, 세션 생성
2. **이벤트 전달** : 서블릿 컨테이너가 해당 이벤트를 리스너에게 전달
3. **로직 수행** : 리스너 내부에 구현된 메서드 실행

---

### 2. 주요 리스너 종류

1. **웹 애플리케이션 생명주기 리스너(`ServletContextListener`)**

```java
서버 시작됨 → Listener 실행
서버 종료됨 → Listener 실행
```

애플리케이션이 시작될 때 DB 커넥션 풀을 초기화하거나, 종료될 때 자원을 반납하는 용도로 가장 많이 사용

1. **세션 관련 리스너(`HttpSessionListener`)**

```java
사용자 접속 → 세션 생성 → Listener 실행
로그아웃 → 세션 종료 → Listener 실행
```

현재 접속자 수 파악, 중복 로그인 방지 등에 사용

1. **속성 변경 리스너**(`ServletRequestListener`)

```java
요청 들어옴 → Listener 실행
요청 끝남 → Listener 실행
```

특정 데이터가 변경될 때 로그를 남기거나 추가적인 비즈니스 로직을 처리