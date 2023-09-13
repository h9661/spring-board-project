# 일반적인 annotation 정리
## @Autowired
`@Autowired`는 Spring Framework에서 사용되는 어노테이션으로, 주로 의존성 주입(Dependency Injection)을 수행할 때 클래스나 필드, 생성자, 메서드에 적용됩니다. 이 어노테이션을 사용하면 Spring 컨테이너가 해당 빈(Bean)에 필요한 의존성을 자동으로 주입해줍니다.

`@Autowired`를 사용하는 이유와 주요 사용 방법은 다음과 같습니다:

1. **의존성 주입(Dependency Injection)**: `@Autowired`는 의존성 주입(DI)을 통해 클래스 간의 느슨한 결합을 실현합니다. 이는 객체 간의 결합도를 낮추고 코드의 재사용성과 테스트 용이성을 향상시킵니다.

2. **의존성 자동 검색**: `@Autowired`를 사용하면 Spring 컨테이너는 해당 타입의 빈을 자동으로 찾아서 주입합니다. 이를테면, 필요한 빈을 직접 생성하거나 검색하지 않아도 됩니다.

3. **생성자, 필드, 메서드 주입**: `@Autowired`는 생성자, 필드(필드 인젝션), 메서드(세터 메서드)에 사용할 수 있습니다. 주로 생성자 주입이 권장되며, 필드나 메서드 주입은 특별한 경우에 사용됩니다.

예제:

```java
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired // 생성자 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        // userRepository를 사용하여 사용자 정보 조회
        // ...
        return user;
    }
}
```

위의 예제에서 `@Autowired`는 `UserService` 클래스의 생성자에 적용되어 `UserRepository` 타입의 빈을 주입합니다. 이를 통해 `UserService` 클래스는 `UserRepository`를 필요로 하는 의존성을 자동으로 처리하게 됩니다.

# Controller annotation 정리
## @RequestBody, @ResponseBody
`@RequestBody`와 `@ResponseBody`는 Spring Framework에서 웹 요청과 응답 데이터를 처리하기 위한 어노테이션입니다. 이 두 어노테이션은 주로 Spring MVC와 함께 사용되며, HTTP 요청 및 응답의 데이터 처리를 간편하게 할 수 있도록 도와줍니다.

1. **@RequestBody**:

   `@RequestBody` 어노테이션은 HTTP 요청의 본문(body)에 있는 데이터를 메서드의 파라미터로 매핑하고, 자바 객체로 변환해줍니다. 이것은 주로 POST 또는 PUT 요청과 함께 사용되며, 클라이언트가 JSON, XML 또는 다른 형식으로 데이터를 전송할 때 유용합니다.

   예제:

   ```java
   @PostMapping("/create")
   public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
       // employee 객체를 사용하여 새로운 직원을 생성
       // ...
       return ResponseEntity.ok("Employee created successfully");
   }
   ```

   위의 예제에서 `@RequestBody`를 사용하여 HTTP 요청의 본문에 있는 JSON 데이터를 `Employee` 객체로 변환하여 사용합니다.

2. **@ResponseBody**:

   `@ResponseBody` 어노테이션은 컨트롤러 메서드의 리턴 값이 HTTP 응답의 본문으로 직렬화되어 클라이언트에게 전송되도록 지정합니다. 주로 JSON 또는 XML 형식으로 데이터를 반환할 때 사용됩니다.

   예제:

   ```java
   @GetMapping("/employee/{id}")
   @ResponseBody
   public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
       Employee employee = employeeService.getEmployeeById(id);
       return ResponseEntity.ok(employee);
   }
   ```

   위의 예제에서 `@ResponseBody`를 사용하여 `Employee` 객체가 HTTP 응답의 본문으로 직렬화되어 클라이언트에게 반환됩니다.

이러한 어노테이션들을 사용하면 웹 요청과 응답 데이터를 쉽게 다룰 수 있으며, 데이터 변환 및 직렬화를 자동으로 처리할 수 있어서 개발자가 복잡한 작업을 수동으로 수행할 필요가 없습니다.

# Repository annotation 정리
## @Repository
`@Repository`는 Spring Framework에서 사용되는 어노테이션으로, 주로 데이터 액세스 계층에서 데이터베이스와의 상호 작용을 담당하는 클래스에 적용됩니다. 이 어노테이션은 Spring의 컴포넌트 스캔 기능과 함께 사용되어 빈으로 등록되며, 데이터 액세스 관련 예외를 스프링의 DataAccessException으로 변환해줍니다.

주요 기능 및 목적은 다음과 같습니다:

1. **데이터 액세스 레이어 구성**: `@Repository`를 사용하여 데이터 액세스 객체를 정의하면 Spring 컨테이너에서 빈으로 관리됩니다. 이를 통해 데이터 액세스 레이어의 구성을 단순화하고 관리할 수 있습니다.

2. **예외 변환**: `@Repository`를 사용하면 데이터 액세스 중 발생할 수 있는 예외들을 스프링의 DataAccessException으로 변환하여 처리할 수 있습니다. 이렇게 하면 일관된 예외 처리가 가능하며, 데이터베이스 관련 예외를 더 쉽게 처리할 수 있습니다.

3. **JPA 및 Hibernate와 통합**: Spring Data JPA와 함께 사용할 때, `@Repository`는 JpaRepository와 함께 데이터베이스와의 상호 작용을 위한 자동 생성된 구현체를 제공합니다. 이를 통해 JPA와 Hibernate를 효율적으로 사용할 수 있습니다.

간단한 예제:

```java
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findUserById(Long id) {
        // 데이터베이스에서 사용자 정보를 조회하는 코드
        // jdbcTemplate 또는 JPA 등을 사용하여 구현
        // ...
        return user;
    }

    public void saveUser(User user) {
        // 사용자 정보를 데이터베이스에 저장하는 코드
        // ...
    }
}
```

위의 예제에서 `@Repository` 어노테이션이 `UserRepository` 클래스에 적용되어 데이터 액세스 레이어를 정의하고 Spring에 의해 빈으로 관리됩니다. 이렇게 하면 데이터베이스와의 상호 작용을 쉽게 구현하고 관리할 수 있습니다.

# Service annotation 정리
## @Service
`@Service`는 Spring Framework에서 사용되는 어노테이션 중 하나로, 주로 비즈니스 로직을 처리하는 서비스 클래스에 적용됩니다. 이 어노테이션을 사용하면 해당 클래스가 Spring의 컴포넌트 스캔에 의해 빈으로 등록되어 Spring 컨테이너에서 관리됩니다. 주요 목적은 다음과 같습니다:

1. **비즈니스 로직 분리**: `@Service` 어노테이션은 비즈니스 로직을 처리하는 클래스임을 나타냅니다. 이를 통해 코드를 더 나누고 관심사를 분리하여 코드의 모듈화와 유지보수성을 향상시킵니다.

2. **의존성 주입(Dependency Injection)**: `@Service` 어노테이션이 적용된 클래스는 Spring의 의존성 주입(DI) 메커니즘을 활용할 수 있습니다. 이를 통해 다른 빈들과 협력하여 비즈니스 로직을 구현할 수 있습니다.

3. **트랜잭션 관리**: Spring에서 `@Service` 어노테이션을 사용하면 트랜잭션 관리가 용이해집니다. Spring은 `@Transactional` 어노테이션과 함께 사용하여 메서드 수준에서 트랜잭션을 제어할 수 있습니다.

간단한 예제:

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        // 사용자 정보를 데이터베이스에서 조회하는 비즈니스 로직
        // userRepository를 사용하여 구현
        // ...
        return user;
    }

    @Transactional
    public void createUser(User user) {
        // 사용자 정보를 데이터베이스에 저장하는 비즈니스 로직
        // userRepository를 사용하여 구현
        // ...
    }
}
```

위의 예제에서 `@Service` 어노테이션이 `UserService` 클래스에 적용되어 비즈니스 로직을 처리하는 서비스 클래스임을 나타내고, Spring 컨테이너에 빈으로 등록됩니다. 이렇게 하면 의존성 주입 및 트랜잭션 관리를 편리하게 수행할 수 있습니다.

# domain annotation 정리
## @Entity
`@Entity`는 Java Persistence API (JPA)에서 사용되는 어노테이션으로, 엔티티 클래스를 정의할 때 사용됩니다. JPA는 자바 애플리케이션에서 관계형 데이터베이스와 상호 작용하는 데 사용되는 ORM (Object-Relational Mapping) 기술 중 하나이며, `@Entity` 어노테이션을 사용하여 Java 클래스를 데이터베이스 테이블과 매핑할 수 있습니다.

`@Entity` 어노테이션을 클래스에 적용하면 해당 클래스가 데이터베이스에서 테이블로 생성되어 저장 및 검색과 같은 데이터베이스 작업에 사용됩니다. 주요 역할과 특징은 다음과 같습니다:

1. **엔티티 클래스 정의**: `@Entity` 어노테이션을 사용하여 자바 클래스를 엔티티로 정의합니다. 이 클래스의 객체는 데이터베이스 테이블의 레코드와 일치합니다.

2. **매핑 정보 제공**: `@Entity` 어노테이션을 사용하여 클래스와 데이터베이스 테이블 간의 매핑 정보를 제공합니다. 이 정보에는 테이블 이름, 컬럼 매핑, 관계 설정 등이 포함됩니다.

3. **JPA 리포지토리와 함께 사용**: Spring Data JPA와 같은 프레임워크에서 `@Entity` 어노테이션이 적용된 클래스를 기반으로 JPA 리포지토리를 생성하여 데이터베이스 작업을 수행할 수 있습니다.

간단한 예제:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    
    // 게터와 세터 메서드
}
```

위의 예제에서 `@Entity` 어노테이션이 `User` 클래스에 적용되었습니다. 이제 `User` 객체는 데이터베이스 테이블과 매핑되며, JPA를 사용하여 데이터베이스 작업을 수행할 수 있습니다. `@Id` 어노테이션은 주요 키(primary key)를 나타내고, `@GeneratedValue` 어노테이션은 키의 생성 전략을 지정합니다.

## @GeneratedValue
`@GeneratedValue` 어노테이션은 JPA (Java Persistence API)에서 엔티티 클래스의 주요 키(primary key)를 자동으로 생성하기 위해 사용되는 어노테이션 중 하나입니다. 이 어노테이션은 주로 데이터베이스 테이블의 자동 증가(auto-increment) 열을 사용하여 엔티티의 주요 키 값을 생성하는 데 사용됩니다.

`@GeneratedValue` 어노테이션을 사용할 때는 주로 두 가지 속성을 고려합니다:

1. **strategy**: `strategy` 속성은 주요 키 생성 전략을 지정합니다. JPA에서는 여러 가지 전략을 제공하는데, 가장 일반적인 것은 다음과 같습니다.
   - `GenerationType.IDENTITY`: 데이터베이스의 자동 증가 열(auto-increment)을 사용하여 주요 키를 생성합니다. 주로 MySQL, PostgreSQL 등과 함께 사용됩니다.
   - `GenerationType.SEQUENCE`: 데이터베이스의 시퀀스(sequence)를 사용하여 주요 키를 생성합니다. 주로 Oracle과 함께 사용됩니다.
   - `GenerationType.TABLE`: 테이블을 사용하여 주요 키 값을 생성합니다. 이 전략은 특수한 경우에 사용됩니다.

2. **generator**: `generator` 속성은 실제로 어떤 주요 키 생성기(generator)를 사용할 것인지 지정합니다. 이 속성은 `strategy`가 `GenerationType.SEQUENCE` 또는 `GenerationType.TABLE`로 설정된 경우에 유효합니다.

간단한 예제:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    
    // 게터와 세터 메서드
}
```

위의 예제에서 `@GeneratedValue` 어노테이션은 `User` 엔티티 클래스의 `id` 필드에 적용되어 주요 키를 자동으로 생성하고, `strategy` 속성이 `GenerationType.IDENTITY`로 설정되었으므로 데이터베이스의 자동 증가 열을 사용하여 주요 키를 생성합니다. 이것은 일반적으로 MySQL과 같은 데이터베이스에서 사용됩니다.

