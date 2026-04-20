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