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

## @PathVariable
`@PathVariable`은 Spring Framework에서 웹 요청의 URL 경로에서 변수를 추출하고 컨트롤러 메서드의 파라미터에 주입하는데 사용되는 어노테이션입니다. 이를 통해 동적인 URL 경로를 처리하고 해당 값을 사용하여 원하는 동작을 수행할 수 있습니다.

간단한 예제를 통해 `@PathVariable`의 사용법을 보여드리겠습니다.

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        // URL 경로에서 추출한 변수(name)를 사용하여 메시지를 생성
        String message = "Hello, " + name + "!";
        return "greeting"; // 뷰 이름 (greeting.html)을 반환
    }
}
```

위의 예제에서 `/hello/{name}` 경로로 요청이 들어오면 `sayHello` 메서드가 실행됩니다. `@PathVariable` 어노테이션은 `name` 파라미터와 경로 변수를 연결합니다. 예를 들어, `/hello/John`에 요청하면 `name` 파라미터에 "John"이 주입되어 "Hello, John!"과 같은 메시지가 생성됩니다.

뷰 템플릿(greeting.html)에서는 이 메시지를 표시할 수 있습니다.

```html
<!DOCTYPE html>
<html>
<head>
    <title>Greeting Page</title>
</head>
<body>
    <h1 th:text="${message}"></h1>
</body>
</html>
```

위의 뷰 템플릿에서 `${message}`는 `sayHello` 메서드에서 생성한 메시지를 출력하는 데 사용됩니다. 이렇게 함으로써 URL 경로의 변수를 추출하고 컨트롤러에서 처리하는 방법을 보여줍니다.

## @RequestParam
`@RequestParam`은 Spring Framework에서 사용되는 어노테이션 중 하나로, HTTP 요청의 파라미터를 메서드의 파라미터로 바인딩하는 데 사용됩니다. 주로 웹 애플리케이션의 컨트롤러 메서드에서 사용되며, HTTP 요청의 쿼리 문자열(query string)이나 POST 요청의 폼 데이터를 가져오는 데에 쓰입니다.

`@RequestParam` 어노테이션을 사용하면 다음과 같이 HTTP 요청 파라미터를 컨트롤러 메서드의 파라미터에 연결할 수 있습니다.

```java
@GetMapping("/example")
public String exampleMethod(@RequestParam("paramName") String parameterValue) {
    // parameterValue 변수에 HTTP 요청 파라미터 "paramName"의 값이 할당됨
    // 이 메서드 내에서 parameterValue 변수 사용 가능
    // ...
}
```

여기서 `@RequestParam("paramName")`은 HTTP 요청에서 "paramName"이라는 파라미터 이름에 해당하는 값을 `parameterValue` 변수에 할당합니다. 이렇게 함으로써 컨트롤러 메서드 내에서 해당 파라미터 값을 사용할 수 있습니다.

`@RequestParam` 어노테이션에는 다양한 옵션과 속성을 사용할 수 있습니다. 예를 들어, 파라미터의 기본값을 설정하거나 필수 파라미터로 지정하거나 여러 값을 배열로 받을 수 있습니다.

예를 들어, 파라미터 기본값을 설정한 예제:

```java
@GetMapping("/example")
public String exampleMethod(@RequestParam(value = "paramName", defaultValue = "default") String parameterValue) {
    // "paramName" 파라미터가 없는 경우 기본값 "default"이 할당됨
    // ...
}
```

`@RequestParam` 어노테이션은 Spring MVC와 함께 주로 사용되며, HTTP 요청의 파라미터 값을 컨트롤러 메서드로 가져오는데 유용합니다.

## @ModelAttribute
`@ModelAttribute`는 Spring Framework에서 사용되는 어노테이션 중 하나로, 주로 컨트롤러 메서드의 파라미터에 적용됩니다. 이 어노테이션은 HTTP 요청의 파라미터를 자동으로 해당 파라미터 타입의 객체로 변환해줍니다. 이를 통해 컨트롤러 메서드에서 파라미터를 객체로 쉽게 다룰 수 있습니다.

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

# ORM
## @OneToMany
`@OneToMany` 어노테이션은 JPA(Java Persistence API)에서 사용되는 양방향 관계를 나타내는데 사용됩니다. 주로 엔티티 클래스 간의 1:N(One-to-Many) 또는 N:1(N-to-One) 관계를 매핑할 때 사용됩니다. 아래는 `@OneToMany` 어노테이션의 사용법과 예제입니다.

1. **일대다(1:N) 관계 설정**:

   `@OneToMany` 어노테이션을 사용하여 일대다 관계를 설정할 때는 다음과 같은 기본적인 사용법이 있습니다.

   ```java
   import javax.persistence.*;
   import java.util.List;
   
   @Entity
   public class ParentEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   
       // 일대다 관계 설정
       @OneToMany(mappedBy = "parent")
       private List<ChildEntity> children;
       
       // Getter, Setter, 기타 메서드
   }
   ```

   위의 코드에서 `ParentEntity`와 `ChildEntity`는 각각 부모 엔티티와 자식 엔티티를 나타내며, `@OneToMany` 어노테이션을 사용하여 이들 간의 관계를 설정하고 있습니다. `mappedBy` 속성은 자식 엔티티 클래스의 필드명을 지정합니다. 이것은 자식 엔티티가 관계의 주인이 아님을 나타냅니다.

2. **카스케이드 설정**:

   `@OneToMany` 어노테이션을 사용할 때 `cascade` 속성을 설정하여 연관된 엔티티의 상태 변경에 대한 옵션을 지정할 수 있습니다. 예를 들어, 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장하고 싶은 경우 `cascade`를 설정할 수 있습니다.

   ```java
   @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
   private List<ChildEntity> children;
   ```

   위의 코드에서 `cascade = CascadeType.ALL`은 모든 상태 변경(저장, 업데이트, 삭제 등)에 대해 자식 엔티티에도 적용된다는 의미입니다.

3. **Fetch 타입 설정**:

   `@OneToMany` 어노테이션에서 `fetch` 속성을 사용하여 데이터를 가져오는 방식(EAGER 또는 LAZY)을 지정할 수 있습니다. 기본적으로는 LAZY로 설정되어 있으며, 필요한 경우 EAGER로 변경할 수 있습니다.

   ```java
   @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
   private List<ChildEntity> children;
   ```

   EAGER로 설정하면 부모 엔티티를 로드할 때 관련된 자식 엔티티도 함께 로드되며, LAZY로 설정하면 자식 엔티티가 실제로 필요한 시점에 로드됩니다.

위의 예제에서는 `@OneToMany` 어노테이션을 사용하여 1:N 관계와 N:1 관계를 설정하고, 관계의 주인과 관련된 필드에 `mappedBy` 속성을 사용하여 엔티티 간의 관계를 정의하고 있습니다. 이를 통해 데이터베이스 스키마와 매핑을 수행하고 JPA를 통해 관련 엔티티를 검색 및 관리할 수 있습니다.

## @ManyToOne
`@ManyToOne` 어노테이션은 JPA(Java Persistence API)에서 사용되는 양방향 관계를 나타내는데 사용되며, 주로 엔티티 클래스 간의 N:1(N-to-One) 관계를 매핑할 때 사용됩니다. 이 어노테이션을 사용하여 엔티티 간의 관계를 설정하는 방법을 알아보겠습니다.

1. **N:1(N-to-One) 관계 설정**:

   `@ManyToOne` 어노테이션을 사용하여 N:1 관계를 설정할 때는 다음과 같은 기본적인 사용법이 있습니다.

   ```java
   import javax.persistence.*;
   
   @Entity
   public class ChildEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   
       // N:1 관계 설정
       @ManyToOne
       @JoinColumn(name = "parent_id")
       private ParentEntity parent;
       
       // Getter, Setter, 기타 메서드
   }
   ```

   위의 코드에서 `ChildEntity`와 `ParentEntity`는 각각 자식 엔티티와 부모 엔티티를 나타내며, `@ManyToOne` 어노테이션을 사용하여 이들 간의 관계를 설정하고 있습니다. `@JoinColumn` 어노테이션을 사용하여 외래 키(Foreign Key)의 이름과 관련된 컬럼을 지정할 수 있습니다.

2. **Fetch 타입 설정**:

   `@ManyToOne` 어노테이션에서 `fetch` 속성을 사용하여 데이터를 가져오는 방식(EAGER 또는 LAZY)을 지정할 수 있습니다. 기본적으로는 EAGER로 설정되어 있으며, 필요한 경우 LAZY로 변경할 수 있습니다.

   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id")
   private ParentEntity parent;
   ```

   EAGER로 설정하면 관련 부모 엔티티가 실제로 필요한 시점에 로드되며, LAZY로 설정하면 부모 엔티티를 실제로 접근할 때 로드됩니다.

3. **Optional 설정**:

   `@ManyToOne` 어노테이션에서 `optional` 속성을 사용하여 관계가 반드시 존재해야 하는지 아니면 선택적(Optional)인지를 지정할 수 있습니다. 기본적으로 `optional = true`로 설정되어 있으며, 이 경우 관계가 없는 경우 `null`이 될 수 있습니다.

   ```java
   @ManyToOne(optional = false)
   @JoinColumn(name = "parent_id")
   private ParentEntity parent;
   ```

   `optional = false`로 설정하면 관계가 반드시 존재해야 하므로 `null`이 허용되지 않습니다.

4. **카스케이드 설정**:

   `@ManyToOne` 어노테이션에서 `cascade` 속성을 사용하여 연관된 엔티티의 상태 변경에 대한 옵션을 지정할 수 있습니다. 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장하고 싶은 경우 `cascade`를 설정할 수 있습니다.

   ```java
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "parent_id")
   private ParentEntity parent;
   ```

   위의 코드에서 `cascade = CascadeType.ALL`은 모든 상태 변경(저장, 업데이트, 삭제 등)에 대해 자식 엔티티에도 적용된다는 의미입니다.

위의 예제에서는 `@ManyToOne` 어노테이션을 사용하여 N:1 관계를 설정하고, 외래 키(Foreign Key) 컬럼을 `@JoinColumn`을 통해 지정하고 있습니다. 이를 통해 데이터베이스 스키마와 매핑을 수행하고 JPA를 통해 관련 엔티티를 검색 및 관리할 수 있습니다.

## @OneToOne
`@OneToOne` 어노테이션은 JPA(Java Persistence API)에서 사용하는 양방향 관계를 나타내는데 사용됩니다. 주로 엔티티 간의 1:1(One-to-One) 관계를 매핑할 때 사용됩니다. `@OneToOne` 어노테이션의 사용법과 예제를 살펴보겠습니다.

1. **1:1 관계 설정**:

   `@OneToOne` 어노테이션을 사용하여 1:1 관계를 설정할 때는 다음과 같은 기본적인 사용법이 있습니다.

   ```java
   import javax.persistence.*;
   
   @Entity
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
   
       // 1:1 관계 설정
       @OneToOne(mappedBy = "employee")
       private Address address;
       
       // Getter, Setter, 기타 메서드
   }
   ```
   
   ```java
   import javax.persistence.*;
   
   @Entity
   public class Address {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String street;
      private String city;
      private String postalCode;
   
       // 1:1 관계 설정
       @OneToOne
       @JoinColumn(name = "employee_id")
       private Employee employee;
   
       // Getter, Setter, 기타 메서드
   }
   ```


   위의 코드에서 `Employee` 엔티티와 `Address` 엔티티는 각각 직원과 주소를 나타내며, `@OneToOne` 어노테이션을 사용하여 이들 간의 관계를 설정하고 있습니다. `mappedBy` 속성은 역방향 관계의 필드 이름을 지정합니다. 이것은 `Address` 엔티티가 관계의 주인이 아님을 나타냅니다.

2. **Fetch 타입 설정**:

   `@OneToOne` 어노테이션에서 `fetch` 속성을 사용하여 데이터를 가져오는 방식(EAGER 또는 LAZY)을 지정할 수 있습니다. 기본적으로는 EAGER로 설정되어 있으며, 필요한 경우 LAZY로 변경할 수 있습니다.

   ```java
   @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
   private Address address;
   ```

   EAGER로 설정하면 관련 주소가 실제로 필요한 시점에 로드되며, LAZY로 설정하면 주소를 실제로 접근할 때 로드됩니다.

3. **Optional 설정**:

   `@OneToOne` 어노테이션에서 `optional` 속성을 사용하여 관계가 반드시 존재해야 하는지 아니면 선택적(Optional)인지를 지정할 수 있습니다. 기본적으로 `optional = true`로 설정되어 있으며, 이 경우 관계가 없는 경우 `null`이 될 수 있습니다.

   ```java
   @OneToOne(mappedBy = "employee", optional = false)
   private Address address;
   ```

   `optional = false`로 설정하면 관계가 반드시 존재해야 하므로 `null`이 허용되지 않습니다.

4. **카스케이드 설정**:

   `@OneToOne` 어노테이션에서 `cascade` 속성을 사용하여 연관된 엔티티의 상태 변경에 대한 옵션을 지정할 수 있습니다. 직원을 저장할 때 주소 엔티티도 함께 저장하고 싶은 경우 `cascade`를 설정할 수 있습니다.

   ```java
   @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
   private Address address;
   ```

   위의 코드에서 `cascade = CascadeType.ALL`은 모든 상태 변경(저장, 업데이트, 삭제 등)에 대해 주소 엔티티에도 적용된다는 의미입니다.

위의 예제에서는 `@OneToOne` 어노테이션을 사용하여 1:1 관계를 설정하고, `mappedBy` 속성을 사용하여 관계의 주인을 지정하고 있습니다. 이를 통해 데이터베이스 스키마와 매핑을 수행하고 JPA를 통해 관련 엔티티를 검색 및 관리할 수 있습니다.