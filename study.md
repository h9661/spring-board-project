# 나만의 체크 포인트

- [x] 글 crud 구현
- [ ] 페이징 구현
- [ ] 사진 여러개 또는 동영상 여러개 올리는 기능 구현. 지금은 사진/파일 하나밖에 안됨
- [ ] 이후 작성 예정

# study note

## dependency injection

의존성 주입(Dependency Injection)은 소프트웨어 디자인 패턴 중 하나로, 객체 간의 의존성을 외부에서 주입(제공)하는 방식을 의미합니다. 이 패턴은 객체 지향 프로그래밍에서 코드를 더 모듈화하고 유지보수하기 쉽게 만들어주며, 특히 Spring Framework와 같은 프레임워크에서 널리 사용됩니다.

의존성 주입의 핵심 아이디어는 다음과 같습니다:

1. **의존성 분리**: 의존성 주입을 통해 객체는 스스로 필요한 의존성(다른 객체 또는 서비스)을 생성하거나 관리하지 않습니다. 대신, 이러한 의존성은 외부에서 주입됩니다.

2. **관심사의 분리**: 이 패턴을 사용하면 객체의 주요 비즈니스 로직과 객체 간의 관계를 설정하는 코드를 분리할 수 있습니다. 이로써 코드는 단일 책임 원칙을 준수하게 되며, 유지보수와 테스트가 용이해집니다.

3. **테스트 용이성**: 의존성 주입은 모의 객체(mock objects) 또는 가짜 객체(fakes)를 사용하여 단위 테스트를 수행하기 쉽게 만듭니다. 주입된 의존성을 간단하게 대체할 수 있으므로 테스트 중에 원하는 동작을 시뮬레이션할 수 있습니다.

Spring Framework와 같은 컨테이너 기반 의존성 주입 프레임워크에서는 주로 다음과 같은 방식으로 의존성을 주입합니다:

1. **Constructor Injection**: 의존성을 클래스의 생성자를 통해 주입합니다. 이는 가장 간단하고 안전한 방법 중 하나로, 객체가 생성될 때 필요한 의존성이 존재해야 한다는 보장을 제공합니다.

ex) 
```java
public class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```


2. **Setter Injection**: 의존성을 클래스의 setter 메서드를 통해 주입합니다. 이 방법은 선택적인 의존성을 가질 때 유용하며, 객체 생성 후 필요한 의존성을 나중에 설정할 수 있습니다.

ex)
```java
public class Car {
    private Engine engine;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

3. **Method Injection**: 특정 메서드를 호출할 때 의존성을 주입합니다. 이 방식은 객체의 메서드 호출 시에만 필요한 의존성을 제공할 때 사용됩니다.

ex)
```java
public class Car {
    private Engine engine;

    public void start() {
        engine.start();
    }
}
```

의존성 주입은 코드의 재사용성, 테스트 가능성 및 유지보수성을 향상시키는 데 도움을 주는 중요한 소프트웨어 디자인 원칙 중 하나입니다.

## Maven 이란?

Maven이란 자바용 프로젝트 관리 도구이다.

!https://velog.velcdn.com/images%2Fchangyeonyoo%2Fpost%2Fcf96cb05-8c93-4bbc-8398-2c2ed259a4a2%2Fimg.png

### 빌드란?

프로젝트를 위해 작성한 Java코드나 여러 자원들(.xml, .jar, .properties)를 JVM이나 톰캣같은 WAS가 인식할 수 있는 구조로 패키징 하는 과정 및 결과물이다.

또 단순히 컴파일해주는 작업 뿐만 아니라, 테스팅, 검사, 배포까지 일련의 작업들을 통틀어 빌드라고 한다.

## Maven

[공식 사이트 : Apache Maven Project](https://maven.apache.org/what-is-maven.html)

Maven은 Apache사에서 만든 빌드툴(build tool)이다.

pom.xml파일을 통해 정형화된 빌드 시스템으로 프로젝트 관리를 해준다.

프로젝트의 전체적인 라이프 사이클을 관리한다.

### 특징

- 빌드 과정을 쉽게 만들기
- 정형화된 빌드 시스템 제공
- Maven은 POM과 플러그인 세트를 사용하여 프로젝트를 빌드한다.
- 양질의 프로젝트 정보 제공
- 더 나은 개발

### 장점

- 편리한 의존성 라이브러리 관리
- 정해진 빌드 방법을 사용하여 협업에서 유리하게 작용
- 다양한 플러그인을 통해 많은 작업이 자동화됨

플러그인을 구동해주는 프레임워크로, 모든 작업은 플로그인에서 수행하게된다.

!https://velog.velcdn.com/images%2Fchangyeonyoo%2Fpost%2F8bbf85c9-a73b-4e28-b823-7a54e1d61abb%2F2.png

## Maven LifeCycle

!https://velog.velcdn.com/images%2Fchangyeonyoo%2Fpost%2Fa2b8a6c6-ef83-4a50-983a-217106124323%2Fimage.png

메이븐은 정해진 라이프 사이클을 통해 프로젝트를 빌드한다.

메이븐 라이프 사이클의 종류는 기본, clean, site가 있다.

각 라이프 사이클 안에는 더 작은 단위의 빌드 단계가 정의되어 있는데 이를 phase라고 한다.

!https://velog.velcdn.com/images%2Fchangyeonyoo%2Fpost%2Fde249c76-f662-4a6b-b7e5-34235db1048e%2F%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jpg

`phase`는 논리적인 빌드 단계이고, 실제로는 `phase`에 연결된 `plug-in`있고 `plug-in`이 수행하는 명령을 `goal`이라고 한다.

라이프 사이클의 세부 순서와 설명은 [이 블로그](https://sjh836.tistory.com/131) 를 참조하면 좋다.

`요약하자면 빌드 순서는 Compile - Test - Package 이다.`

Clean -> init -> compile -> test-compile -> test -> package -> integration-test -> verify -> install -> deploy -> site

## Maven 설정 파일

### settings.xml

- 메이븐 빌드 툴과 관련된 설정파일
- `Maven_HOME/conf`에 위치

### POM

프로젝트마다 하나의 pom.xml파일이 있다.

프로젝트의 모든 설정, 의존성 등을 설정할 수 있다.

pom.xml의 엘리트먼트에 대한 설명은 [이 블로그](https://goddaehee.tistory.com/199)를 참고하면 좋다.

### pom.xml 엘리트먼트

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>kr.or.connect</groupId>
    <artifactId>examples</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mysample</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

- project : pom.xml 파일의 최상위 루트 엘리먼트(Root Element)입니다.
- modelVersion : POM model의 버전입니다.
- groupId : 프로젝트를 생성하는 조직의 고유 아이디를 결정합니다. 일반적으로 도메인 이름을 거꾸로 적습니다.
- artifactId : 해당 프로젝트에 의하여 생성되는 artifact의 고유 아이디를 결정합니다. Maven을 이용하여 pom.xml을 빌드할 경우 다음과 같은 규칙으로 artifact가 생성됩니다. artifactid-version.packaging. 위 예의 경우 빌드할 경우 examples-1.0-SNAPSHOT.jar 파일이 생성됩니다.
- packaging : 해당 프로젝트를 어떤 형태로 packaging 할 것인지 결정합니다. jar, war, ear 등이 해당됩니다.version : 프로젝트의 현재 버전. 추후 살펴보겠지만 프로젝트가 개발 중일 때는 SNAPSHOT을 접미사로 사용합니다. Maven의 버전 관리 기능은 라이브러리 관리를 편하게 합니다.
- name : 프로젝트의 이름입니다.
- url : 프로젝트 사이트가 있다면 사이트 URL을 등록하는 것이 가능합니다.

해당 엘리먼트 안에 필요한 라이브러리를 지정하게 됩니다.

## Application Context
https://mangkyu.tistory.com/151

## spring.xml
https://velog.io/@junbee/SpringMVC-%EA%B8%B0%EB%B3%B8-xml-%ED%8C%8C%EC%9D%BC-%EC%84%A4%EB%AA%85

## actuator
Spring Boot Actuator는 Spring Boot 기반 애플리케이션의 운영 환경에서 모니터링 및 관리를 위한 기능을 제공하는 확장 기능입니다. 이를 통해 애플리케이션의 상태, 상태 지표, 환경 설정 등을 관찰하고 관리할 수 있습니다. Spring Boot Actuator는 애플리케이션의 운영 측면에서 중요한 도구로 사용됩니다.

Spring Boot Actuator가 제공하는 주요 기능과 엔드포인트는 다음과 같습니다:

1. **Health Endpoint**: `/actuator/health`
    - 애플리케이션의 상태를 나타내는 엔드포인트로, 애플리케이션의 상태가 "UP" 또는 "DOWN"으로 표시됩니다. 데이터베이스 연결, 메시지 큐 연결 등과 같은 외부 리소스와의 연결 상태를 확인할 때 유용합니다.

2. **Info Endpoint**: `/actuator/info`
    - 애플리케이션에 대한 추가 정보를 제공하는 엔드포인트로, 버전 정보, 환경 설정 값, 사용자 정의 메타데이터 등을 포함할 수 있습니다.

3. **Metrics Endpoint**: `/actuator/metrics`
    - 메트릭 정보를 조회하는 엔드포인트로, 애플리케이션의 성능 및 상태 지표를 모니터링할 수 있습니다. 예를 들어, 메모리 사용량, HTTP 요청 수, 데이터베이스 쿼리 실행 횟수 등의 지표를 확인할 수 있습니다.

4. **Trace Endpoint**: `/actuator/trace`
    - HTTP 요청의 추적 정보를 조회하는 엔드포인트로, 요청과 응답의 상세 정보를 확인할 수 있습니다. 디버깅 및 문제 해결에 유용합니다.

5. **Environment Endpoint**: `/actuator/env`
    - 애플리케이션의 환경 설정을 조회하는 엔드포인트로, 프로퍼티 및 환경 변수 등의 설정 정보를 확인할 수 있습니다.

6. **Shutdown Endpoint**: `/actuator/shutdown`
    - 애플리케이션을 graceful하게 종료하기 위한 엔드포인트입니다. 보안을 위해 기본적으로 비활성화되어 있으며, 활성화하려면 추가 설정이 필요합니다.

Spring Boot Actuator는 운영 환경에서 애플리케이션의 상태를 실시간으로 모니터링하고 문제를 진단하며, 애플리케이션의 성능을 개선하는 데 도움을 줍니다. 이러한 기능은 애플리케이션을 운영하고 유지보수하는 과정에서 매우 유용합니다.

## post 데이터 받기
node는 bodyParser의 도움을 받아서 req.body에 데이터를 넣어주는 반면, spring은 데이터를 Controller 매개변수에 다 넣어서 준다. 편하게 그냥 객체를 넣으면, 알아서 해당 객체의 변수에 맞게 데이터를 넣어준다.
그게 싫으면 그냥 form의 name에 맞게 매개변수를 만들어서 받아주면 된다.

## Model
`Model`은 Spring Framework에서 웹 애플리케이션의 데이터를 처리하고 뷰(View)에 전달하는 데 사용되는 인터페이스입니다. 주로 Spring MVC (Model-View-Controller) 아키텍처에서 컨트롤러(Controller)와 뷰(View) 사이에서 데이터를 전달하는 역할을 합니다.

`Model` 인터페이스는 `addAttribute(String attributeName, Object attributeValue)` 메서드를 통해 데이터를 저장하고, 이 데이터는 뷰에 전달됩니다. 이렇게 저장된 데이터는 HTML 템플릿에서 변수처럼 사용할 수 있으며, 동적 웹 페이지를 생성할 때 유용합니다.

간단한 Spring MVC 컨트롤러에서 `Model`을 사용한 예제를 보여드리겠습니다:

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello(Model model) {
        // Model을 사용하여 데이터를 저장하고 뷰에 전달
        model.addAttribute("message", "Hello, Spring MVC!");
        return "hello"; // 뷰 이름 (hello.html)을 반환
    }
}
```

위의 예제에서 `/hello` 경로로 요청이 들어오면 `hello` 메서드가 실행되고, `Model`을 통해 "message"라는 속성에 "Hello, Spring MVC!" 문자열을 저장합니다. 그리고 "hello" 문자열을 반환하면 이것은 뷰 이름을 나타내며, Spring은 "hello.html" 뷰 템플릿을 렌더링하면서 `Model`에 저장된 데이터를 뷰에 전달합니다.

`hello.html` 뷰 템플릿에서는 `${message}`와 같이 `${}` 표현식을 사용하여 `Model`에 저장된 데이터를 출력할 수 있습니다.

## 쿼리 파라미터 받는 법 @RequestParam
Spring에서 Query Parameter를 사용하기 위해서는 주로 `@RequestParam` 어노테이션을 컨트롤러 메서드의 파라미터에 적용합니다. Query Parameter는 URL 뒤에 `?`를 사용하여 전달되는 이름-값 쌍의 데이터입니다. 예를 들어, `/search?query=Spring&category=programming`와 같은 URL에서 `query`와 `category`가 Query Parameter입니다.

아래는 Spring에서 Query Parameter를 사용하는 예제와 설명입니다:

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(@RequestParam(name = "query", required = false) String query,
                         @RequestParam(name = "category", required = false) String category,
                         Model model) {
        // query와 category를 사용하여 검색 작업 수행

        // 검색 결과를 모델에 추가
        model.addAttribute("query", query);
        model.addAttribute("category", category);

        return "searchResults"; // 검색 결과를 표시하는 뷰로 이동
    }
}
```

위의 예제에서 `/search` 경로로 GET 요청이 들어오면 `search` 메서드가 실행됩니다. 이 메서드는 `@RequestParam` 어노테이션을 사용하여 `query`와 `category` Query Parameter를 파라미터로 받습니다. `name` 속성을 사용하여 Query Parameter의 이름을 지정하고, `required` 속성을 사용하여 필수 여부를 지정할 수 있습니다. `required = false`로 설정하면 해당 Query Parameter가 없는 경우에도 메서드가 실행됩니다.

이후, `search` 메서드는 `Model`에 검색 결과를 추가하고, `searchResults`라는 뷰로 이동합니다. 검색 결과는 해당 뷰에서 표시될 수 있습니다.

URL에서 Query Parameter를 전달하려면 `/search?query=Spring&category=programming`와 같이 URL에 Query Parameter를 포함시키면 됩니다. 이러한 Query Parameter를 컨트롤러 메서드의 파라미터로 수신하고 처리할 수 있습니다.

## 타임리프로 Model에서 받은 값으로 동적 속성 url 만들기
태그에 SSR 속성 만들기 -> th:attr=${}
동적 url 생성 -> <th:action or th:href>="@{abcd/{var}(var=${...})}"

## 타임리프로 Model에서 받은 값으로 html 자바스크립트에서 사용하기
```
<script th:inline="javascript">
    /*<![CDATA[*/
    var message = [[${message}]];
    var url = [[${url}]];

    alert(message);
    location.replace(url);
    /*]]>*/
</script>
```

## 테이블 ALTER 예시들
MySQL에서 테이블을 변경하는 방법은 여러 가지가 있습니다. 테이블을 변경하려면 다음과 같은 일반적인 작업을 수행할 수 있습니다.

1. **열 추가하기:**
   ```sql
   ALTER TABLE table_name
   ADD column_name datatype;
   ```

2. **열 수정하기:**
   ```sql
   ALTER TABLE table_name
   MODIFY column_name new_datatype;
   ```

3. **열 삭제하기:**
   ```sql
   ALTER TABLE table_name
   DROP COLUMN column_name;
   ```

4. **기본 키 추가하기:**
   ```sql
   ALTER TABLE table_name
   ADD PRIMARY KEY (column_name);
   ```

5. **외래 키 추가하기:**
   ```sql
   ALTER TABLE table_name
   ADD FOREIGN KEY (column_name) REFERENCES referenced_table(referenced_column);
   ```

6. **테이블 이름 변경하기:**
   ```sql
   RENAME TABLE old_table_name TO new_table_name;
   ```

7. **테이블 삭제하기:**
   ```sql
   DROP TABLE table_name;
   ```

8. **테이블 엔진 변경하기:**
   ```sql
   ALTER TABLE table_name
   ENGINE = new_engine;
   ```

이 명령어를 사용하여 필요한 변경을 수행할 수 있습니다. SQL 쿼리를 실행할 때 주의해야 하며, 중요한 데이터를 변경하기 전에 백업을 만드는 것이 좋습니다. 변경 내용은 신중하게 검토하고 테스트한 후에 적용해야 합니다.

## file 업로드하기
1. <input type="file" name="file"> 태그로 파일 입력 받기
2. @RequestParam("file") MultipartFile file Controller에서 파일 받기
3. service에서 파일 처리하는 로직 작성하기. 파일 저장될 경로 설정, 파일 이름 설정, 파일 저장 등..(코드 참고)


## 사진 업로드 미리보기
```jsx
document.querySelector('input[type="file"]').addEventListener('change', function (event) {
   var img = document.querySelector('#img-preview');
   var file = event.target.files[0];
   var reader = new FileReader();

   reader.onload = function (e) {
       img.setAttribute('src', e.target.result);
   };

   reader.readAsDataURL(file);
});
```
이 코드는 HTML과 JavaScript를 사용하여 파일 업로드(input 태그) 이벤트를 처리하고, 선택한 이미지 파일을 미리보기로 표시하는 간단한 스크립트입니다. 코드의 주요 부분을 설명하겠습니다:

1. `document.querySelector('input[type="file"]')`: HTML 문서에서 `input` 요소 중 `type`이 "file"인 요소를 선택합니다. 이것은 파일 업로드를 위한 `<input type="file">` 요소를 선택하는 것입니다.

2. `addEventListener('change', function (event) { ... })`: 파일 업로드 input 요소에 'change' 이벤트 리스너를 추가합니다. 이 이벤트는 사용자가 파일을 선택하고 업로드 input의 값이 변경될 때 발생합니다.

3. `var img = document.querySelector('#img-preview');`: HTML 문서에서 `id`가 "img-preview"인 이미지 요소를 선택합니다. 이 이미지는 선택한 파일의 미리보기를 표시할 때 사용됩니다.

4. `var file = event.target.files[0];`: 이벤트 객체인 `event`의 `target`에서 선택한 파일 목록을 가져온 후, 첫 번째 파일을 선택합니다. 이 코드는 하나의 파일만 업로드하는 경우에 사용됩니다.

5. `var reader = new FileReader();`: `FileReader` 객체를 생성합니다. 이 객체는 파일을 읽는 데 사용됩니다.

6. `reader.onload = function (e) { ... }`: `FileReader` 객체의 `onload` 이벤트 핸들러를 정의합니다. 파일이 읽혔을 때 실행됩니다.

7. `reader.readAsDataURL(file);`: `FileReader`를 사용하여 선택한 파일(`file`)을 데이터 URL로 읽어옵니다. 이 데이터 URL은 이미지 파일을 Base64로 인코딩한 문자열입니다.

8. `img.setAttribute('src', e.target.result);`: 읽어온 데이터 URL(`e.target.result`)을 이미지(`img`)의 `src` 속성에 설정하여 미리보기 이미지로 표시합니다.

이 코드는 파일 업로드 input에서 파일을 선택하면 해당 파일을 읽어 미리보기 이미지로 표시합니다. 이러한 미리보기 기능은 사용자 경험을 향상시키고 업로드할 파일이 올바른지 미리 확인할 때 유용합니다.


## 파일 저장 경로 설정법(configuration)
```java
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///C:/Users/gusck/spring 프로젝트/demo/files/");
    }

}
```
static에 파일을 보관하면, 서버 실행 시 한번만 로드하기 때문에 동적으로 파일을 불러올 수 없다. 따라서 이렇게 파일을 저장하는 곳을 따로 둬야 동적으로 파일들을 불러올 수 있다.

## 파일을 여러개 업로드하는 법
https://cbw1030.tistory.com/391

## 스프링 파일 업로드 제한 해제 방법
스프링은 파일 업로드하는 데에 제약이 있다 이것을 바꾸는 방법을 알아보자.

   ```java
   @Configuration
   public class FileUploadConfig {
   
       @Bean
       public MultipartConfigElement multipartConfigElement(){
           MultipartConfigFactory factory = new MultipartConfigFactory();
           factory.setMaxFileSize(DataSize.ofMegabytes(10L));
           factory.setMaxRequestSize(DataSize.ofMegabytes(10L));
   
           return factory.createMultipartConfig();
       }
   }
   ```

## 양방향 순환 참조 문제
https://dev-coco.tistory.com/133

