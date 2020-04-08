# Mybatis

마이바티스 : 객체지향 언어인 자바의 관계형 데이터베이스 프로그래밍을 좀더 쉽게 할수 있게 해주는 개발 프레임워크

자바는 jdbc api를 제공해주지만, 이런 JDBC를 이용하면 1개 클래스에 반복되는 코드가 존재,한 파일에 java언어와 sql 언어가 섞여있어서 재사용성이 안좋다

Mybatis는 jdbc의 이러한 단점을 개선했으며, 개발자가 작성한 sql 명령어와 자바 객체를 매핑해주는 기능을 제공하며, 기존에 사용하던 sql 명령어를 재사용한다.

### 특징

- 짧은 자바 코드로 db연동 처리
- sql 명령어를 자바 코드에서 분리하여 XML 파일에 따로 관리

## mybatis 구조

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile2.uf.tistory.com%2Fimage%2F99E306495A4CCD831EE1A6)

- mybatis-config는 mybatis의 메인 환경설정 파일이다. 어떤 DBMS와 커넥션 맺을지 , 어떤 맵퍼 파일들이 있는지 등 알 수 있다.
- Mybatis는 맵퍼 파일에 있는 각 SQL  명령어들을 Map에 담아서 저장하고 관리한다.

```xml
<!--mybatis-config.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.Member"/>
        <package name="com.Article"/>
        <package name="com.Candidate"/>
        <package name="com.Comment"/>
    </typeAliases>
</configuration>
```

```xml
<!--mapper.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.Article">
    <select id="select_all" resultType="ArticleVO">
    SELECT * FROM happydaram2.article
    </select>
</mapper>

```

- config파일에서 package name으로  해당 패키지 안의 클래스를 이용하게 해준다.

  



## mapper.xml 

```xml

<mapper namespace="com.Article">
    <select id="select_one" resultType="ArticleVO" parameterType="int">
        SELECT * FROM happydaram2.article WHERE article_type=#{article_type}
    </select>
</mapper>
<!--#{article_type}은  DAO에서 입력받은 parameter이다. parameter가 존재하면 xml 파일의 select에서 parameterType 지정해줘야한다.-->

```

- 동적 쿼리

## SqlSession 사용

- Mybatis 에서는 `SqlSession`을 생성하기 위해 `SqlSessionFactory`를 사용한다. 세션을 한번 생성하면 매핑 구문을 실행하거나 커밋 또는 롤백을 하기 위해 세션을 사용할수 있다. 마지막으로 더 이상 필요하지 않은 상태가 되면 세션을 닫는다. `Mybatis 스프링 연동모듈`을 사용하면 `SqlSessionFactory`를 직접 사용할 필요가 없다.
- SqlSessionTemplate : mybatis 스프링 연동모듈
- SqlSession을 구현하고 코드에서 SqlSession을 대체하는 역할
- 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할수 있다.

##  SqlSessionTemplate과 DAO 연동

- ```java
  @Repository
  public class SampleDao {
  
      @Autowired
      private SqlSessionTemplate sqlSession;
  
      public void insert(String queryId, Object parameterObject) {
          sqlSession.insert(queryId, parameterObject);
      }
  }
  ```

  - @Repository : DAO에 다는 어노테이션
  - SqlSessionTemplate으로 객체를 주입받는다.
  - insert함수는 SqlSession.insert(queryId,vo); 고정이다.

## SqlSessionFactory 설정

- ```xml
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <property name="dataSource" ref="dataSource"></property>
          <property name="mapperLocations" value="classpath:mapper/sql/*.xml"></property>
          <property name="configLocation" value="classpath:mybatis-config.xml"></property>
      </bean>
  
      <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
          <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"></constructor-arg>
      </bean> 
  ```


## sqlSession 함수들

```java
<T> T selectOne(String statement, Object parameter)
<E> List<E> selectList(String statement, Object parameter)
<T> Cursor<T> selectCursor(String statement, Object parameter)
<K,V> Map<K,V> selectMap(String statement, Object parameter, String mapKey)
int insert(String statement, Object parameter)
int update(String statement, Object parameter)
int delete(String statement, Object parameter)
```

- Session은 여러가지 함수 가지고 있다.(리턴값에 따라 달라진다.)
- selectOne : 하나의 객체만 리턴한다. (없는지 있는지 판단할때 개수 리턴하게 하는 selectone 쓰는게 좋다.)
- selectList :  객체 list를 리턴한다.
- selectMap : map 객체를 리턴한다.
- insert : 삽입
- update : update
- delete : 삭제

## DAO 작성 (sqlsession 이용해서 sql 문들과 매핑)

```java
@Repository
public class ArticleDAO {

    @Autowired
    SqlSessionTemplate sqlSession;

    String namespace="com.Article";

    public List<ArticleVO> select_list(int article_type){
        List<ArticleVO> list=null;
        if(article_type==0)//전체 글
            list=sqlSession.selectList(namespace+".select_all");
        if(article_type!=0){//게시판별
            list=sqlSession.selectList(namespace+".select_board",article_type);
        }
        return list;
   
    
    //session.selectList(namespace+".select_all");
    //    해서 com.Article.select_all있는 곳과 매핑해서  //	list값으로 결과값 받는다.
```

