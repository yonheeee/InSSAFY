## Front Controller Pattern

### 등장 배경

컨트롤러 마다 공통으로 처리 가능한 로직을 설계<br>
어떤 컨트롤러로 들어올지 모르기 때문<br> 
이로 인해, 공통 코드 중복, 유지보수의 어려움<br> 그러면서 등장한 것이 Front Controller Pattern

---

### Front Controller Pattern

```java
모든 요청 → Front Controller → 공통 처리 → 분기
```
**장점**

- **단일 진입성** : 모든 요청을 Front Controller에서 접수해 요청 처리 일관
- **공통 처리** : 모든 작업 front controller를 거쳐감
- **유연한 확장성** : 새로운 요청 처리를 추가할 때 기존의 구조를 크게 변경하지 않고 확장 가능
- **코드 간결성** : 여러 개의 Servlet을 만드는 번거러움 줄고 코드 가독성 향상

---

### 요청의 구분

1.**url에 언제나 “특정 작업”을 의미하는 파라미터 추가**

“파라미터로 기능 구분”

```java
/member?action=select
/member?action=insert
/member?action=delete
```

2.**와일드 카드를 이용한 url 매핑**

“URL 자체로 기능 구분”

```java
/member/select
/member/insert
/member/delete
```
<br><br>

## 코드로 보는 Front Controller Pattern

### 실습 BACK_DB_WS_01_1

#### **1. Front Controller Pattern**

```java
@WebServlet("/main")
public class MainServelt extends HttpServlet
```

- `/main` 으로 들어오는 요청 먼저 다 받음
- 내부에서 `process()` 로 보냄
- 그 다음 어떤 기능을 할지 분기

---

#### 2. 분기 기준

```java
String action = request.getParameter("action");
```

`action` 은 프론트 컨트롤러가 읽는 명령어

---

### 3. 실제 분기 처리

```java
switch (action) {
case "regist":
    deRegist(request, response);
    break;
}
```

- `action` 값을 보고
- 알맞는 매서드로 보내는 거야

---

### 코드 흐름
```java
사용자 요청
→ /main 으로 들어옴
→ MainServelt 가 받음
→ action 값 확인
→ regist면 deRegist() 호출
→ 응답 반환
```