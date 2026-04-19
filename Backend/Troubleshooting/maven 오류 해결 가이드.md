## Maven 오류해결 가이드

### 1. 라이브러리 빨간줄 (import 안됨)

**원인**

- dependency 다운로드 실패
- Eclipse 동기화 문제
- `.m2` 캐시 깨짐

 **해결**

```
1. Maven → Update Project
2. Project → Clean
3. Eclipse 재시작
```

**그래도 안되면**

```
4. .m2 폴더 삭제
5. Eclipse 재실행
6. Maven Update (재다운로드)
```

---

### 2. dependency 추가했는데 적용 안됨

**원인**

- pom.xml 반영 안됨
- Maven 동기화 안됨

**해결**

```
1. pom.xml 저장
2. Maven → Update Project
```

**그래도 안되면**

```
dependency 주석 → Update → 다시 해제 → Update
```

---

### 3. 다운로드 중 깨짐 (특히 와이파이)

**원인**

- 네트워크 끊김
- jar 일부만 다운로드됨

**증상**

- class not found
- import 안됨
- 이상한 빨간줄

**해결**

```
.m2 해당 라이브러리 폴더 삭제
→ Maven Update
```

**심하면 전체 삭제**

```
.m2/repository 삭제
```

---

### 4.Eclipse만 빨간줄인데 실행은 됨

**원인**

- Eclipse 버그 / 동기화 문제

**해결**

```
1. Maven Update
2. Project Clean
3. Eclipse 재시작
```

---

### 5. dependency 충돌 (버전 꼬임)

**원인**

- 서로 다른 버전 라이브러리 사용

**증상**

- NoSuchMethodError
- ClassNotFoundException

**해결**

```
pom.xml에서 버전 통일
불필요한 dependency 제거
```
---

## 핵심 트러블 패턴

문제 생기면 거의 이 순서로 해결됨 

```
1. Maven Update
2. Project Clean
3. Eclipse 재시작
4. .m2 삭제 후 재다운로드
```