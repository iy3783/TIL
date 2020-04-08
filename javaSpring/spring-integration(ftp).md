# spring ftp adapter 설정하기

1. maven dependency  설정

   ```xml
   <dependency>
       <groupId>org.springframework.integration</groupId>
       <artifactId>spring-integration-ftp</artifactId>
       <version>5.2.4.RELEASE</version>
   </dependency>
   
   
   version에서 무조건 spring-framework의 버젼보다 낮은것을 사용해야 한다. => 아니면 인식하지 못한다.
   ```

2. application-context.xml 추가하기

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"   xmlns:int="http://www.springframework.org/schema/integration"
   xmlns:int-ftp="http://www.springframework.org/schema/integration/ftp"
   xmlns:context="http://www.springframework.org/schema/context"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/integration/ftp
   https://www.springframework.org/schema/integration/ftp/spring-integration-ftp.xsd
   http://www.springframework.org/schema/integration
   https://www.springframework.org/schema/integration/spring-integration.xsd">
   
   </beans>
   
   <!--xml 네임스페이스에서 int ,  int-ftp 두가지가 핵심이다. int 는 integration의 message ftpchannel 빈을 사용할 수 있게 한다. int-ftp는 outbound-adapter 등 사용할 수 있게 한다.-->
   ```

3. ftp session factory (applicationContext 파일에 그대로 입력)

   ```xml
   <bean id="ftpClientFactory"
     class="org.springframework.integration.ftp.session.DefaultFtpSessionFactory">
       <property name="host" value="localhost"/>
       <property name="port" value="22"/>
       <property name="username" value="kermit"/>
       <property name="password" value="frog"/>
       <property name="clientMode" value="0"/>
       <property name="fileType" value="2"/>
       <property name="bufferSize" value="100000"/>
   </bean>
   
   
       <int:channel id="ftpChannel">
       </int:channel>
       <int-ftp:outbound-channel-adapter  id="ftpOutbound"
                                          channel="ftpChannel"
                                          remote-directory="/www"
                                          session-factory="ftpClientFactory">
           <int-ftp:request-handler-advice-chain>
               <int:retry-advice />
           </int-ftp:request-handler-advice-chain>
       </int-ftp:outbound-channel-adapter>
   
   
   ```

   

4.  MultipartFile을 http method로 받았을때 spring mvc 에서 이용하기 위해 MultipartResolver 사용한다.

```xml
applicationContext 파일
<!--MultipartResolver-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>

```



5.  Multipartfile 받고 ftp로 upload하는  Controller 제작

   ```java
       @RequestMapping( value = "/article/image", consumes = "multipart/form-data" ,method = RequestMethod.POST)
       @ResponseBody
       public String article_image(@RequestPart("file")@RequestParam(value="File", required=true) MultipartFile multipartFile) {
           String returnValue;
           try {
               ConfigurableApplicationContext ctx =
                       new ClassPathXmlApplicationContext("applicationContext.xml");
               MessageChannel ftpChannel = ctx.getBean("ftpChannel", MessageChannel.class);
   
               File convFile = new File(multipartFile.getOriginalFilename());
               convFile.createNewFile();
               FileOutputStream fos = new FileOutputStream(convFile);
               fos.write(multipartFile.getBytes());
               fos.close();
   
   
               final Message<File> message = MessageBuilder.withPayload(convFile)
                       .build();
               ftpChannel.send(message);
               returnValue="success";
               ctx.close();
           }catch(Exception e){
               returnValue = "fail";
               e.printStackTrace();
           }
   
           return returnValue;
       }
   ```

   - Requestparam으로 Multipart/form-data 형식으로 multipartfile 받는다.

   - ftpchannel bean을 주입하고 integration 특성인 메시지만든다(메시지는 헤더와 payload로 구성되고 전달할 파일을 payload에 넣고 메시지보내면 ftp에 upload 할 수 있다.)

   - MultipartFile은 메시지에 못 넣기 때문에 MultipartFile을 File형식으로 바꾼다.

   - ```java
     			File convFile = new File(multipartFile.getOriginalFilename());
                 convFile.createNewFile();
                 FileOutputStream fos = new FileOutputStream(convFile);
                 fos.write(multipartFile.getBytes());
                 fos.close();	
     ```
     
   - multipartfile 을 여러개 받고 싶으면 @Requestparam("files") MultipartFile[] files 로 받는다.
   
   - testing 시     post ,  body부분에    key값을  requestparam 안의 value로  value를 form-data file형식으로 받고 key값이 같은것이 있으면 전부 list 로 인식을 한다.