# Spring and microservice

> 마이크로서비스 구축 유튜브 영상(java brain)

## microservices vs service oriented archiectures

- SOA(service oriented architecture) :  어플리케이션의 기능들을 비즈니스적으로 구분하고 묶어서 하나의 표준 interface를 통해서 서비스 하는 아키텍쳐
  - service consumer : 서비스 사용자 (클라이언트인 엔드유저 , 다른 서비스)
  - service Provider : 서비스 제공자 요청에 맞는 서비스 제공
  - service registry : 서비스의 정보를 저장 , provider가 registry를 통해 consumer에게 서비스를 제공한다.
  - 이러한 consumer와 provider는 아키텍쳐 내의 모듈도 될 수 있다.
  - => 각 모듈별로 나누어져 있는것은 MSA와 비슷하다.
- MSA :  SOA의 부분 집합과 같다.
- SOA MSA 차이점
  - SOA는 모듈의 의존성은 줄이되 모듈 내에서 공유할 수 있는건 최대한 공유하는 정책을 사용한다.
  - SOA는 서비스의 FLOW를 유지하려하지만,MSA는 FLOW의 구별을 요구한다.=> 서비스 자체에서 독립된 모듈이 아닌 서비스 자체를 모듈별로 전부 나누어 버린다.
  - SOA는 재사용에 중점을 두지만 MSA는 각 서비스의 독립을 추구한다.

## WEBFLUX

- url비동기 요청을 보낼때 쓰는 라이브러리

#### Reactive system

- 4가지 조건이 있는 시스템
  - Responsive : 주어진 시간에 반응하는 시스템
  - Resilient : 장애가 발생해도 주어진 시간에 반응하는 시스템
  - Elastic : 요청이 많아져도 주어진 시간에 반응하는 시스템
  - Message Driven : 메세지 기반



- RestTemplate

- WebClient

- ```xml
  <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-webflux</artifactId>
      </dependency>
  
  ```

- ```java
   RestTemplate restTemplate = restTemplateBuilder.build();
    
          StopWatch stopWatch = new StopWatch();
          stopWatch.start();
    
          String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
          System.out.println(helloResult);
    
          String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
          System.out.println(worldResult);
    
          stopWatch.stop();
          System.out.println(stopWatch.prettyPrint());
    
  ```

- restTemplate 는 동기적으로 데이터 받아온다.

- ```java
  @Component
  public class RestRunner implements ApplicationRunner {
  
      @Autowired
      WebClient.Builder webClientBuilder;
  
      @Override
      public void run(ApplicationArguments args) throws Exception {
          WebClient webClient = webClientBuilder.build();
  
          StopWatch stopWatch = new StopWatch();
          stopWatch.start();
  
          Mono<String> helloMono = webClient.get().uri("http://localhost:8080/hello")
                                      .retrieve().bodyToMono(String.class);
          helloMono.subscribe(s-> {
              System.out.println(s);
              if(stopWatch.isRunning()){
                  stopWatch.stop();
              }
              System.out.println(stopWatch.prettyPrint());
              stopWatch.start();
          });
  
          Mono<String> worldMono = webClient.get().uri("http://localhost:8080/world")
                                      .retrieve().bodyToMono(String.class);
          worldMono.subscribe(s -> {
              System.out.println(s);
              if(stopWatch.isRunning()){
                  stopWatch.stop();
              }
              System.out.println(stopWatch.prettyPrint());
              stopWatch.start();
          });
      }
  }
  
  
  ```

- webclient는 비동기적으로 데이터 요청



#### WebClient

- WebClient는 Netty등의 비동기 통신 라이브러리를 기반으로 한다.

- 이벤트 루프 구조를 사용하므로 최대 스레드에 대한 관리등의 고민을 할 필요가 없다.

- 기본적으로 비동기 처리이므로 REST 호출 후  추가적으로 기다릴 필요가 없을 때나 다른 처리와의 의존성이 없을 때 다른 처리가 이 REST 호출을 기다리지 않아도 된다.

- WebClient는 builder를 통해 생성하며 builder는 thread-safe 하지 않으므로 각 호출 혹은 Injection Point에 대해서 별도의 인스턴스를 생성해야 한다. 

  - ```java
    @Service
    public class TestService {
        private final WebClient webClient;
    
        public TestService(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("http://www.test.com").build();
        }
        //이런식으로 사용하면 된다.
    ```

  - web flux 사용하면 WebClient.Builder의 의존성은 Spring이 처리해준다. Builder만들 필요는 없다.

  - ```java
    
    		long userId = 10;
      
            Mono<String> result = webClient
                    .get()
                    .uri(String.format("/getuser", userId))
                    .retrieve()
                    .bodyToMono(String.class);
      
            result.subscribe(response -> {
      			System.out.println("response : " + response);
            }, e -> {
                System.out.println("error message : " + e.getMessage());
            });
    ```

  -   String result = webClient

    ​        .get()

    ​        .uri(String.format("/getuser", userId))

    ​        .retrieve()

    ​        .bodyToMono(String.class);

  - retrieve를 사용하면 그냥 응답내용을 받고 exchange()사용하면 헤더등의 부가 정보를 얻을 수 있다.

  - bodyToMono(String.class).block()으로 쓰면 동기식으로 가능 RestTemplate처럼

  - subscribe()

## 하드 코딩하면 안되는 이유

- changes require code updates
- Dynamic urls in the cloud
- load balancing
- Multiple environments
- 이런 것들 때문에 service discovery 필요하다.(패턴찾기)
- Eureka server 이용하도록 하자

## Eureka

- Eureka : 마이크로 서비스들의 정보를 레지스트리에 등록할 수 있도록 하고 마이크로 서비스의 동적인 탐색과 로드 밸런싱을 제공한다.

- Eureka server , Eureka client
  - Eureka server는 Eureka client에 해당하는 마이크로 서비스들의 상태 정보가 등록되어 있는 레지스트리를 갖는다.
  - Eureka Client의 서비스가 시작 될 때 Eureka Server에 자신의 정보를 등록한다. 등록된 후에는 30초 마다 레지스트리에 ping을 전송하여 자신이 가용 상태임을 알리는데 일정 횟수 이상 ping이 확인되지 않으면 Eureka Server에서 해당 서비스를 레지스트리에서 제외시킨다.
  - 레지스트리의 모든 정보는 Eureka Client에 복제되어 있어 필요 할 때마다 가용 상태인 모든 서비스들의 목록 확인할 수 있고 이 목록은 30초 마다 갱신된다. 가용 상태의 서비스 목록을 확인 할 경우에는 서비스의 이름을 기준으로 탐색하며 로드밸런싱을 위해 내부적으로 Ribbon(클라이언트 측의 로드 밸런서)을 사용한다. 
  - ![](https://t1.daumcdn.net/cfile/tistory/99D5AA3F5C5C265933)
  
  ####  spring cloud 적용 과정 : Eureka
  
- dependency 추가

- ```xml
  org.springframework.cloud:spring-cloud-starter-netflix-eureka-server
  ```

-  application.yml 파일에 유레카 서버 설정에 대한 정보를 추가한다.

- ```yaml
  # -- Server Port
  
  server:
    port: 8787
  
  # -- Eureka
  eureka:
    instance:
      hostname: 127.0.0.1
    client:
      serviceUrl:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      register-with-eureka: false
      fetch-registry: false
  ```

- main함수 클라스에@EnableEurekaServer 어노테이션 추가

- localhost:8787 웹페이지 들어가면 registry 사이트 보인다.

- ```java
  @LoadBalanced
  @Bean(name = "onvernodesRestTemplate")
  
  public RestTemplate commonRestTemplate() {
  
    final RestTemplate restTemplate = new RestTemplate();
    // Code ...
    return restTemplate;
  }
  ```

- 마이크로 서비스 통신을 위해 RestTemplate를 사용하는데 Bean부분에 @LoadBalanced 어노테이션을 달아서 사용한다.

  ### 주의사항

  ```java
  @Autowired
  @Qualifier("overnodesRestTemplate")
  
  private RestTemplate restTemplate;
  
  private String userServiceName = "USER-SERVICE";
  
  private String apiPath = "/get-path";
  
  public JsonObject get(){
  
    return restTemplate.getForEntity("http://" + userServiceName + apiPath, JsonObject.class).getBody();
  
  }
  ```

  - url 적는 부분은 앞으로 전부 유레카에 등록된 서비스의 이름으로 사용해야 한다.

## ZUUL

- 모든 마이크로서비스에 대한 요청을 받아들이고 라우팅하는 프록시 API Gateway 기능을 수행한다. 

- ZUUL또한 Eureka client이다.

- 의존성 

  - ```xml
    org.springframework.cloud:spring-cloud-starter-netflix-zuul
    ```

- application.yaml

  - ```yaml
    # -- Default spring configuration
    
    spring:
      application:
        name: api-gateway-service
    
    # -- Eureka client
    eureka:
      client:
        serviceUrl:
          defaultZone: ${EUREKA_URL:http://127.0.0.1:8787/eurek
    
    # -- Zuul
    
    zuul: 
     routes:
      user-service:
       path: /user/**
       service-id: USER-SERVICE
    ```

- main함수 있는 클래스

- ```java
  @EnableZuulProxy
  
  @EnableDiscoveryClient
  @SpringBootApplication
  
  
  public class ApiGatewayServiceApplication {
    public static void main(String[] args) {
      SpringApplication.run(ApiGatewayServiceApplication.class, args);
    }
  }
  ```

  - @EnableZuulProxy ,@EnableDiscoveryClient 추가한다.

- RestTemplate 요청 URL을 apigateway이름으로 바꾸자

