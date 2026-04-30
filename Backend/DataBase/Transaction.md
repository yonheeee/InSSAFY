## 트랜잭션

**데이터를 안전하게 처리하기 위한 작업 묶음**

---

### 왜 트랜잭션이 필요한가?

**예 : 계좌이체**

**트랜잭션이 없다면**

```jsx
UPDATE A SET money = money - 10000; 
UPDATE B SET money = money + 10000;
```

→ 중간에 장애가 발생하면

- A: -10000
- B : +10000

**데이터 깨짐**

**트랜잭션이 있다면** 

```jsx
BEGIN;

UPDATE A SET money = money - 10000;
UPDATE B SET money = money + 10000;

COMMIT;
```

- 둘 다 성공하면 → commit
- 하나라도 실패하면  → rollback

---

## 트랜잭션 4대 특징(ACID)

### 1. Atomicity(원자성)

- **전부 성공 or 전부 실패**

### 2. Consistency(일관성)

- **DB 규칙은 항상 유지됨**
    
    ex ) 잔액은 음수가 될 수 없음, 외래키 무결성 유지
    

→ **트랜잭션 끝나도 DB는 항상  “정상 상태”**

### 3. Isolation(격리성)

- **여러 트랜잭션이 서로 간섭하지 않음**

### 4. Durability(지속성)

- **COMMIT하면 영구 저장됨**

---

## 논리적 캐시(Snapshot)

- **격리된 캐시** : DBMS가 DB가 접속하면 lock하면서 connection에 대한 메모리 내의 트랜잭션만의 개별 공간(캐시/버퍼)가 만들어짐
- **가시성 차이 :** 내가 수정한 캐시 데이터를 포함한 최신 상태 봄
    - 다른 사용자는 커밋하기 전 원본 DB의 이전 데이터(Snapshot)만 봄

| **종류** | **정식 명칭** | **용도** | **호환성** |
| --- | --- | --- | --- |
| **공유 잠금** | **Shared Lock (S)** | 읽기(`SELECT`) 작업 시 발생 | 다른 S-Lock과 **동시 실행 가능** |
| **배타적 잠금** | **Exclusive Lock (X)** | 쓰기(`DML`) 작업 시 발생 | 다른 모든 Lock을 **차단함** |
- **commit 시**, 트랜잭션 캐시에 있는 것을 DB에 저장
- **rollback 시,** 트랜잭션 캐시에 있는 내용을 날림
- **savepoint 시**, 포인트를 찍는 곳 까지 이후 캐시를 날름

---

## 격리성 문제

### Dirty Read

- **커밋 안 된 데이터를 읽음**

```jsx
A: 돈 -10000 (아직 COMMIT 안함)
B: 그걸 읽음
A: ROLLBACK

B는 존재하지 않는 데이터 봄
```

### Non-Repeatable Read

- **같은 조회인데 값이 바뀜**

```jsx
A: SELECT → 10000
B: UPDATE → 20000 + COMMIT
A: SELECT → 20000
```

### Phantom Read

- **없던 행이 생김**

```jsx
A: WHERE salary > 3000 → 3명
B: INSERT → +1명
A: 다시 조회 → 4명
```

| 수준 | 설명 | 문제 허용 |
| --- | --- | --- |
| READ UNCOMMITTED | 커밋 안된 것도 읽음 | Dirty Read |
| READ COMMITTED | 커밋된 것만 읽음 | Non-repeatable |
| REPEATABLE READ | 같은 값 유지 | Phantom |
| SERIALIZABLE | 완벽 | 없음 |

---

## MVCC(Multi Version Concurrency Control)

- **데이터를 여러 버전으로 관리해서 동시에 읽기/쓰기를 충돌 없이 처리하는 방식**

### 동작 원리

**① 데이터 변경 시 (Update)**

1. 데이터 페이지 : 해당 레코드의 값을 새 값으로 변경
2. Undo 로그 : 변경 전 'Old 데이터'를 Undo 영역에 기록
3. 시스템 칼럼 : 변경한 트랜잭션 ID를 내 트랜잭션 ID로 업데이트

**② 데이터 조회 시 (Select)**

1. 지금 실행 중인 트랜잭션 ID 확인 후, 이전에 커밋된 데이터만 보여줌
2. 현재 다른 트랜잭션에 의해 수정 중이라면, Undo 로그에 있는  'Old 데이터' 보여줌

### 장단점

**장점**

- 높은 동시성 : 읽기 작업과 쓰기 작업을 차단하지 않아 동시성이 좋음
- 일관성 : 트랜잭션 중 데이터가 바뀌어도 내가 보던 시점의 데이터 유지

**단점**

- 저장 공간 : 이전 버전 데이터(Undo 로그)를 보관해야 하므로 추가적인 디스크/메모리 공간이 필요
- 관리 오버헤드 : 더 이상 필요 오래된 Undo 로그를 삭제하는 프로세스(Purge)가 별도로 필요