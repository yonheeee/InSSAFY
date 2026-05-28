## AJAX(Asynchronous JavaScript and X)

### Synchronous VS Asynchronous

| **특성**      | **동기(Synchronous)**                   | **비동기(Asynchronous)**                 |
| ------------- | --------------------------------------- | ---------------------------------------- |
| **처리 방식** | 순차적 실행(단순한 코드 흐름)           | 병렬적 실행(상대적으로 복잡한 코드 구조) |
| **자원 활용** | 단일 작업으로 인한 자원 비효율성        | 다중 작업 수행으로 인한 자원 효율성      |
| **대기 상태** | 다음 작업을 위한 대기 시간 발생         | 대기 시간 없이 다른 작업 수행 가능       |
| **코드 실행** | 위에서 아래로 순차적 실행               | 콜백함수나 프로미스 등을 통한 실행       |
| **적용 상황** | 간단한 연산이나 작은 규모의 데이터 처리 | 네트워크 요청이나 대용량 파일 처리       |

### AJAX

- **비동기로 처리되는 JavaScript와 XML(초창기는 XML을 데이터 교환 수단으로 사용) 통신 방법**
- 화면 갱신 없이 클라이언트와 서버 간 XML, JSON, HTML 등 정보 교환

![image.png](attachment:e63c5d15-8170-41e1-a458-7c9a7a114a25:image.png)

1. **이벤트 발생(Client)**
    - 사용자가 `duck` 라는 아이디로 타이핑, 키보드에서 손을 뗄 때마다(`onkeyup`) 이벤트 발생
2. **요청 객체 생성 및 콜백 생성(Client)**
    - `XMLHttpRequest` : 자바스크립트는 서버와 통신을 담당하는 일꾼 객체 생성
    - 응답이 돌아오면 실행할 `function callback()` (콜백 함수)를 이 일꾼에게 미리 쥐여줌
3. **서버로 비동기 요청 전송(Client → Server)**
    - `validate?id=duke` : 일꾼이 브라우저 뒤편에서 서버의 `ValidateServlet` 을 향해 “이 duke라는 아이디 쓸 수 있어?”라고 요청을 보냄
4. **서버의 데이터 비즈니스 로직 처리(Server)**
    - `User Database` 조회 : 서버(`ValidateServlet`)는 데이터베이스를 조회하여 duke라는 아이디가 이미 있는지 존재하는지 확인
5. **응답 데이터 전송(Server → Client)**
    - `<message>invalid</message>` : 서버는 검증 결과인 `invalid` 라는 데이터를 XML 형식의 메세지에 담아 다시 브라우저 `XMLHttpRequest` 일꾼에게 돌려 보냄
6. **콜백 함수 호출(Client)**
    - `function callback()` 실행 : 데이터가 무사히 도착한 것을 확인한 브라우저는, 2단계에서 미리 등록했던 콜백을 깨워 다시 실행
7. **화면 업데이트(Client)**
    - `Invalid ID` 출력 : 콜백함수 내부 코드(`update HTML DOM`)가 실행되면서, 화면 전체를 새로 바꾸지 않고 입력창 옆에 안내 문구만 끼움