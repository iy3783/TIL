# Date Formatter

- 자바에서 VO 만들때 데이터베이스의  시간 포맷을 결정해주기 위해 SimpleDateFormat  객체 생성

- ```java
  SimpleDateFormatter formatter = new SimpleDateFormatter(yyyy-MM-dd hh:mm:ss);
  Calendar cal = Calendar.getInstance();
  String today = formatter.format(cal.getTime());
  //formatter는 calendar 객체를 string 형태 날짜로 바꿔준다.
  
  
  ```

- Timestamp : 날짜정보를 가지는 객체이다.

- ```java
  String today = formatter.format(cal.getTime());
  
  Timestamp create_time;
  Timestamp update_time=Timestamp.valueOf(today);
  
  ```

- 

