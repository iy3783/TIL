# SwaggerUI

- 자동화된 API 문서 작성 툴

- com 디렉토리(소스파일들) 에 swagger 설정파일넣으면 끝

- ```java
  @Configuration
  @EnableSwagger2
  public class SwaggerConfig {
  
      @Bean
      public Docket api() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .select()
                  .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                  .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
                  .build();
      }
  }
  ```

- localhost:8080/swagger-ui.html

- html 페이지에서 실제로 테스팅도 가능하다.

  

