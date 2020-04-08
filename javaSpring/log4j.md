# Log4j 

> Log4j.xml 설정 방법 숙지   
>
> <appender>      <layout>       patternLayout 방식      <logger> 
>
> 로그 레벨 숙지  
>
> 요즘에는 slf4j 많이 쓴다.

- pom.xml log4j 디펜던시 추가

- log4j.xml  설정파일을 생성

  - Logger : 로깅 메세지를 appender에 전달, 개발자가 직접 로그 출력 여부를 런타임에 조정 , 로그 레벨을 가지고 있으며 로그 출력 여부는 로그문의 레벨과 로거의 레벨을 가지고 결정

    - log4j 레벨
      - fatal: 시스템 심각한 문제 ,일반적으로 안씀
      - error : 에러
      - warn : 나중에 에러 원인가능
      - info : 로그인 , 상태변경과 같은 정보성메시지
      - debug : 개발자 디버그용
      - trace  : 상세한 디버그 모드

  - Appender : 로그의 출력위치를 결정(파일 ,콘솔 ,DB등)

  - Layout : appender가 어디에 출력할 것인지 결정했다면 어떤 형식으로 출력할 것인지 출력 layout을 결정

    ```xml
       <appender name="console" class="org.apache.log4j.ConsoleAppender">
            <param name="Target" value="System.out"/>
            <layout class="org.apache.log4j.PatternLayout">
                <param name="ConversionPattern" value="%-5p: %c - %m%n"/>
            </layout>
        </appender>
    ```

    - appender name 에 출력장소 쓰고
    - appender안의 layout의 param안에 패턴값을 넣는다.
    - name="patternLayout" : 일반적인 로그
      - %p : debug,info,error,warn,fatal 등의 priority 출력
      - %m : 로그 내용출력
      - %n : 개행문자 (플랫폼종속적인)
      - %c  : 카테고리 출력

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">
  <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/"
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                       xsi:schemaLocation="http://jakarta.apache.org/log4j/ ">
  
      <!-- Appenders -->
      <appender name="console" class="org.apache.log4j.ConsoleAppender">
          <param name="Target" value="System.out"/>
          <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="%-5p: %c - %m%n"/>
          </layout>
      </appender>
  
      <!-- Application Loggers -->
      <logger name="com">
          <level value="info"/>
      </logger>
  
      <!-- 3rdparty Loggers -->
  
      <logger name="org.springframework.web">
          <level value="info"/>
      </logger>
  
      <!-- Root Logger -->
      <root>
          <priority value="warn"/>
          <appender-ref ref="console"/>
      </root>
  
  </log4j:configuration>
  
  ```