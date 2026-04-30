## Index

- **데이터의 검색 효율성을 높이기 위한 목적의 데이터 베이스 객체**
- 하나 이상의 컬럼을 기준으로 **정렬**하여 데이터의 빠른 검색을 지원
- 테이블 기반으로 **선택적으로 생성**
- Mysql은 제약 조건 인덱스 자동 생성
    - Primary Key
    - Unique
    - Foreign Key

### 장단점

장점

- 조회 속도 향상(특히 대량 데이터 처리 시 효과적)

단점

- 잦은 DML은 인덱스 엔트리 변경을 동반해 성능 저하 발생
- Random Access
- 추가적 저장 공간 필요

---

### B-Tree

- Balanced Tree
- 모든 리프 노드가 같은 깊이에 존재
    - 모든 리프 노드까지의 탐색 깊이가 일정하여 일관된 성능을 보장
- 각 노드가 자식 노드에 대한 포인터뿐만 아니라 실제 데이터를 함께 가질 수 있도록 설계된 탐색 트리

![image.png](B-tree)

### B+Tree

- MySQL InnoDB에서의 인덱스 구조
- 데이터는 리프 노드에만 저장
- 리프 노드는 연결리스트 형태로 연결 : Range Scan 최적

![image.png](B+tree.png)

### Clustered Index

- 리프 노드에 Key와 record 함께 저장되는 구조
- 일반적으로 Key는 PK
    - MySQL의 경우 Key 선택 규칙
    - PK 컬럼 → Not null unique 컬럼 → 고유한 ㄱ밧을 가지는 컬럼을 내부적으로 만들어서 사용
- Key가 record 저장 위치는 결정
- 테이블당 단 하나만 존재 가능

![image.png](ClusteredIndex.png)

### Scondary Index

- Clustered Index 이외에 추가로 생성하는 인덱스로 보조 인덱스라고 함
- 리프 노드에 Key와 PK가 함께 저장되는 구조
- Key lookup 필요
    - PK로 재탐색 해야함
- 테이블당 여러 개 생성 가능

---

## Index 설계

### Concatenated Index

- 여러 컬럼을 하나로 묶어서 만드는 인덱스
- 여러 컬럼의 조합으로 하나의 인덱스 생성

#### 핵심 개념: Leftmost Prefix Rule

- 복합 인덱스는 맨 앞 컬럼부터 순서대로 사용할 때만 제대로 동작
- B+Tree 구조
    - 인덱스는 정렬된 상태
    - 선택도 높은 컬럼을 앞에 배치

### 인덱스 설계 시 주의 사항

- WHERE, JOIN, ORDER BY, GROUP BY절에 자주 사용되는 컬럼 고려
- 컬럼의 분포도가 10~15% 이내인 경우 적용하면 좋음
    - 중복되는 값이 적을수록 분포도가 좋음
    - 카드널리티가 높은 컬럼 우선 고려
- 복합 컬럼 인덱스 고려
    - 점 조건 앞에, 선분 조건은 마지막 위치
- 커버링 인덱스 고려
- 정기적인 모니터링과 그에 따른 최적화 필요