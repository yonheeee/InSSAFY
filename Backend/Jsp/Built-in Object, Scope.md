## Built-in Object(내장 객체)

- `.jsp` 파일의 `_jspService` 메서드 **내부에 로컬 변수로 이미 선언된 객체**
    - 스크립트 영역에서 동일한 이름으로 로컬 변수를 선언할 수 없음

| 변수명 | 변수 타입 | 용도 |
| --- | --- | --- |
| response | javax.servlet.http.HttpServletResponse | 서버의 응답 정보 |
| out | javax.servlet.jsp.JspWriter | 서버의 응답 정보 출력 스트림 |
| pageContext | javax.servlet.jsp.PageContext | 현재 JSP 페이지에 대한 정보 |
| request | javax.servlet.http.HttpServletRequest | 클라이언트 요청 정보 |
| session | javax.servlet.http.HttpSession | HTTP 세션에 대한 정보 |
| application | javax.servlet.ServletContext | 웹 애플리케이션에 대한 정보 |
| exception | java.lang.Throwable | isErrorPage=true인 경우 사용되는 예외 객체 |
| config | javax.servlet.ServletConfig | JSP 페이지에 대한 설정 정보 저장 |

<br><br>

## Scope(데이터 유지 범위)

### 1. page scope

- **현재 JSP 페이지 안**에서 사용 가능
- 페이지 끝나면 사라짐

```jsx
pageContext.setAttribute("msg", "hello");
```

**특징**

- **가장 좁은 범위**

---

### 2. request scope

- **한 번의 요청 동안 유지**
- **Controller → JSP 데이터 전달**

```jsx
request.setAttribute("msg", "hello");
```

**특징**

- **forward 시 유지**
- 응답 끝나면 사라짐

---

### 3. session scope

- **사용자 접속 동안 유지**

```jsx
session.setAttribute("msg", "hello");
```

**특징**

- **로그인 상태 유지**
- 브라우저 꺼지거나 만료되면 사라짐

---

### 4. application scope

- **서버 전체에 공유**

```jsx
application.setAttribute("count", 1);
```

**특징**

- 모든 사용자 공유
- 서버 종료까지 유지

---

### Scope 표 정리
| scope | 범위 | 유지 시간 | 사용 예 |
| --- | --- | --- | --- |
| page | 현재 JSP | 페이지 끝까지 | 거의 안씀 |
| request | 요청 1번 | 응답까지 | 데이터 전달  |
| session | 사용자 | 로그인 동안 | 로그인 |
| application | 전체 | 서버 종료까지 | 전역 데이터 |