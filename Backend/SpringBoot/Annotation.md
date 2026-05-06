## Annotation

`@Service`  : **비즈니스 로직을 수행하는 클래스를 나타내는데 사용**

- 비즈니스 로직 처리 : Controller로부터 요청을 받아 DB 접근(Repository)전후로 필요한 계산, 데이터 가공, 예외 처리 담당
- `@Repository` , `@Controller` 처럼 `@Component` 를 확장한 형태(Spring 컨테이너가 애플리케이션 실행 시 자동으로 Bean으로 등록)
- 서비스 인터페이스 구현체 클래스 상단에 선언
- `@Service("빈이름")` 형식으로 이름을 수동으로 지정기도 함
    - 인터페이스의 구현체가 여러 개일 경우도 존재

`@Repository` : **DB와 상호작용하는 클래스를 나타내는데 사용**

- 컴포넌트 스캔 대상 : `@Component` 의 특수화된 형태, 빈으로 등록
- 데이터베이스 드라이버마다 다른 저수준 예외(SQL 예외 등)를 Spring의 일관된 액세스 예외 계층(`DataAccessException`)으로 변환해 줌
- DB에 접근하여 CRUD를 담당하는 DAO클래스나 Repository인터페이스 구현체 상단에 선언

`@Autowired` : **의존성 주입을 자동으로 처리**

- 빈의 타입이 일치하는 객체를 찾아 주입
    - 동일한 타입이 여러 개면, `@Qualifier` 참고하여 결정
- 객체 간의 관계 설정 파일(XML)을 적지 않고, 어노테이션만으로 의존성 해결
- 필드, 생성자 또는 Setter 메서드에 붙여서 사용 가능

`@Qualifier` : `@Autowired` **가 어떤 빈을 주입해야 할지 명확하게 지시하는 역할**

- `@Qualifier("지정한이름")` 으로 등록된 빈들 중 특정 이름 가진 빈을 가져옴

`@Configuration` : **설계도이자 중심점 역할**

- 설계도 선언 : 해당 클래스가 스프링 컨테이너의 설정 정보 담고 있음 의미
- 빈 등록 제어 : `@Bean` 어노테이션을 사용해 메서드 단위로 객체 생성하고 스프링 컨테이너 등록
- 싱글톤으로 관리
- `@Configuration`은 `@ComponentScan`과 함께 사용됩니다.

`@ComponentScan` : **스프링이 관리할 빈을 찾기 위해 특정 경로 스캔**

- 지정된 패키지와 그 하위 패키지 돌며 특정한 어노테이션 붙은 클래스 찾아 컨테이너에 빈으로 등록
    - `@Component` (기본)
    - `@Service` (비즈니스 로직)
    - `@Repository` (데이터 액세스)
    - `@Controller` / `@RestController` (웹 요청 처리)
    - `@Configuration` (설정 정보)