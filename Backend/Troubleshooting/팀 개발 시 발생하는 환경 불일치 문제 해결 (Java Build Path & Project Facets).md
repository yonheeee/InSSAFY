## 팀원 간 라이브러리 불일치 해결 방법

### 문제 상황

- 팀원 간 **Java 버전 또는 라이브러리 설정이 다를 경우**
- 프로젝트 실행 시 오류 발생
- 라이브러리 경로 깨지면 → **Missing 에러 발생**

---

#### **해결 방법 (Eclipse 기준)**

1. **Java Build Path 확인**

```
Window → Preferences → Java → Build Path
```

또는

```
프로젝트 우클릭 → Properties → Java Build Path
```

---

**2. 라이브러리 및 JDK 버전 확인**

- 설치된 JDK/JRE 버전 확인
- 팀원과 동일한 버전으로 맞추기
- 외부 라이브러리(.jar) 정상 연결 여부 확인

---

**3. Missing 에러 해결**

- 라이브러리가 없으면 **Missing** 표시됨
- 해결 방법:
    - 올바른 라이브러리 다시 추가
    - JDK 경로 재설정

---

**4. Module Path / Classpath 수정**

- Edit 클릭 후 잘못된 라이브러리 제거 가능
- 필요 시:
    - Module Path → Classpath 변경
    - 또는 반대로 설정

→ Java 9 이상에서 모듈 문제 자주 발생

---

**5. 변경 사항 적용**

```
Apply → Apply and Close
```
<br><br>

## Project Facets로 버전 맞추는 방법

### 문제 상황

- 팀원 간 **Java / Dynamic Web Module 버전 다를 때**
- 서버 (Tomcat 등)랑 버전 안 맞을 때
- 실행은 되는데 **서블릿 관련 오류 날 때**

---

### 해결 방법

1. **Project Facets 들어가기**

```
프로젝트 우클릭 → Properties → Project Facets
```

---

1. **버전 확인 및 수정**
- **Java**
- **Dynamic Web Module**
- (필요시)JavaScript

팀원과 동일하게 맞추기

예:

```
Java : 11
Dynamic Web Module : 4.0
```

---

3. Apply 적용

```
Apply → Apply and Close
```

<br><br>
## Build Path vs Facets 차이

| 구분 | 역할 |
| --- | --- |
| Java Build Path | 라이브러리, JDK 설정 |
| Project Facets | 프로젝트 “버전/기능” 설정 |

-> 둘 다 맞아야 정상 동작