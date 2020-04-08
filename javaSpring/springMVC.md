# spring mvc 디렉토리 구조

- src
  - main
    - java
      - com
        - 각종 컨트롤러들
          - 컨트롤러 : uri 정보에 맞는 컨트롤러가 db와 연동하게해줌
          - DAO (data access object) :   추상적인 인터페이스제공하는 객체 ,  어플리케이션 호출을 데이터 저장부분에 매핑함으로써 DAO는 데이터 베이스 세부내용을 노출하지 않고 특정 데이터 조작 기능을 제공한다. DAO에게 데이터베이스 접근을 시켜서 많은 원격호출의 오버헤드를 줄일수있다(인터페이스 역할)
          - VO (value object) :  rdb의 레코드에 대응되는 자바 클래스
    - resources(정적 파일들) : 안에 sql 문 용도의 xml 파일과 log4j.xml
      - mapper.sql : sql용 xml 파일들
      - 로그용 xml
      - mybatis-config.xml : mybatis를 이용해서 sql 문을 직접쓰는게 아닌 xml 파일로 작성
    - webapp
      - WEB_INF
        - views: jsp 파일(restful -api server는 뷰가 필요없다. )
        - bean용 xml 파일 (applicationContext.xml 이름은 자유)
        - dispatcher-servlet.xml
        - web.xml
- pom.xml



## pom.xml 

- pom.xml 

  - groupId :  당신의 프로젝트를 모든 프로젝트 사이에서 고유하게 식별하게 해주는 것 . package 명명 규칙을 따른다.

  - artifactId : 버전정보를 생략한 jar 파일의 이름 ,

    서드파티 jar 파일이라면 할당된 이름만 사용하자

  - version :  버젼정보

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>Crawler</groupId>
  <artifactId>Macro</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>Macro Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

```

이정도는 기본이다.(공통사항)

- 이 pom.xml의기본 사항을 적은것 

- ```xml
  <properties>
      <java-version>1.8</java-versionjava-version>
      <org.springframework-version>4.3.12.RELEASE</org.springframework-version>
      <org.aspectj-version>1.6.10</org.aspectj-version>
      <org.slf4j-version>1.6.6</org.slf4j-version>
      <project.build.sourceEncoding>	UTF-8</project.build.sourceEncoding>
  	<maven.compiler.source>1.7</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  <!--properties는 변수처럼 사용가능 {변수} 이런식으로 -- >
  ```

- ```xml
  <dependencies>
      <!--Spring-->
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${org.springframework-version}</version>
          <exclusions>
              <exclusion>
                  <groupId>commons-logging</groupId>
                  <artifactId>commons-logging</artifactId>
              </exclusion>
          </exclusions>
      </dependency>
      <!--commons-logging은 slf4j쓰기위해 그냥 exclusion하자-->
        <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${org.springframework-version}</version>
      </dependency>
  </dependencies>
  ```

- 많이 쓰는 라이브러리들 (groupId , artifactId )

  - org.springframework의  `spring-webmvc`
  - org.springframework의 `spring-context`
  - org.aspectj 의 `aspectjrt`  : AOP용
  - org.slf4j의 `slf4j-api`   : 로그
  - log4j 의 `log4j` : 로그 for 자바
  - javax.inject 의 `javax.inject`
  - javax.servlet 의 `servlet-api`
  - javax.servlet.jsp 의` jsp-api`
  - javax.servlet 의 ` jstl`
  - junit 의 `junit`
  - org.mariadb.jdbc 의 `mariadb-java-client`
  - org.bgee.log4jdbc-log4j2 의` log4jdbc-log4j2-jdbc4.1`
  - org.mybatis 의 `mybatis`
  - org.mybatis의 `mybatis-spring`
  - org.springframework의 `spring-jdbc`
  - commons-dbcp의 `commons-dbcp`
  - org.jsoup의` jsoup`
  - io.springfox의  `spring-fox-swagger2`
  - io.springfox의 `springfox-swagger-ui`
  - commons-codec의 `commons-codec`
  - com.googlecode.json-simple의 `json-simple`
  -  \+ 나머지 빌드 세팅(메이븐 플러그인들)