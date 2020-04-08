# 자바 기본 문법

- 출력
- 변수(데이터 타입)
- 주석
- 형변환
- 조건문
- 반복문
- 메소드
- 클래스
- 상속
- overriding
- abstract
- interface
- 예외
- 제네릭

## 출력

- ```java
  System.out.print("얘는 그냥출력 , 줄바꿈 없다");
  System.out.println("얘는 마지막에 줄바꿈");
  ```

- 입력: 

  - ```java
    Scanner scanner = new Scanner(System.in);
    String message;
    message = scanner.nextLine(); // 키보드 문자입력
    int a  = scanner.nextInt();
    
    ```

## 변수(데이터 타입)

- 자바는 정적 데이터 타입 
- 데이터 타입 종류
  - byte : 1byte
  - short : 2byte
  - int : 4byte
  - long : 8byte
  - float : 4byte 실수
  - double : 8byte 실수
  - char 2byte 모든 유니코드 문자

## 형변환

-  데이터의 타입을 변경하는 것
- 데이터의 크기가 작은 것에서 큰 것으로 바뀌면 자동 형변환 된다.

- 그 반대의 상황은 에러 , 명시적 형변환을 해주어야 한다.

- ```java
  float a=3.0f;
  double b = a;  //자동 형변환
  ```

- ```java
  float a = (float)100.0; // double 값을 강제로 float으로
  int a = (int)100.0f;
  ```

## 조건문/반복문

- ```java
  //조건문
  
      if (true) {
              System.out.println(1);
          } else {
              System.out.println(2);
        
          
          //if else 아니면 
          // switch case 문 아니면
          // int a = (true) ? 1:2  3항연산자를 사용한다.
  ```

- ```java
  //반복문
  for(int i =0 ; i < 5 ; i++){
  
  }
  List<int> ls;
  for(int a : ls){
  	ls안의 값들을 int a로 받아옴
  }
  while(true){
      
  }
  ```

# 메소드

- java는 전부 클래스로 되어있다.

- 클래스 안의 함수를 매소드라고 부름

- ```java
  public class Method1 {
      public static void numbering() {
          int i = 0;
          while (i < 10) {
              System.out.println(i);
              i++;
          }
      }
   
      //매소드는 parameter 가지거나 return value 가질 수 있다.
      
      
      public static void main(String[] args) {
          numbering();
      }
  }
  ```

# 클래스

- 클래스 :  객체 찍어내기 위한 툴

  - 맴버변수 + 맴버 함수

  ```java
  package org.opentutorials.javatutorials.object;
   
  class Calculator{
      int left, right;
        
      public void setOprands(int left, int right){
          this.left = left;
          this.right = right;
      }
        
      public void sum(){
          System.out.println(this.left+this.right);
      }
        
      public void avg(){
          System.out.println((this.left+this.right)/2);
      }
  }
    
  public class CalculatorDemo4 {
        
      public static void main(String[] args) {
            
          Calculator c1 = new Calculator();
          c1.setOprands(10, 20);
          c1.sum();       
          c1.avg();       
            
          Calculator c2 = new Calculator();
          c2.setOprands(20, 40);
          c2.sum();       
          c2.avg();
      }
  }
  ```

- 변수들 : 멤버변수

- 함수들 : 매소드

- 메인 메소드 : 딱 한개만 있다

  

- 생성자 

  - 맴버변수들 초기화 하는 함수(데이터 리턴벨류 없다.)

  - ```java
     public Manager(String empNo, String name, String part, String position) 
        {
            // set대신 상위클래스 멤버 필드 가져오기
            super(empNo, name, part);
            this.position = position;
        }
    ```

-  **super**

  - 클래스에서 상위 클래스 **객체**를 가리키는 포인터

  - 상위 클래스와 하위 클래스 모두 동일안 메소드 또는 변수가 있을때 상위 클래스 멤버 지지

  - 생성자에서만 호출가능

  - 멤버 필드 접근 : super.멤버필드

  - 멤버 메소드 접근 : super.멤버메소드(매개변수)

  - 생성자 접근 : super(매개변수) - 생성자 메소드에서만 사용이 가능

- static 붙은 메소드 = 클래스 매소드

# 상속

-  상속을 하면 부모의 맴버변수나 함수를 이용가능하다.(

-  클래스의 재사용성을 위해 사용하는것

- ```java
  public class Employee 
  {
      private String empNo;    // 사원번호
      private String name;    // 이름
      private String part;    // 부서
      
      // 매개변수가 없는 생성자
      public Employee()    {    }
      
      // 매개변수 3개를갖는 생성자
      public Employee(String empNo, String name, String part)
      {
          this.empNo = empNo;
          this.name = name;
          this.part = part;
      }
      
      // getter와 setter를 이용한 값을 정해주고 반환해주기
      public String getEmpNo() { return empNo; }
   
      public void setEmpNo(String empNo) { this.empNo = empNo; }
   
      public String getName() { return name; }
   
      public void setName(String name) { this.name = name; }
   
      public String getPart() { return part; }
   
      public void setPart(String part) { this.part = part; }
      
      // 멤버 필드의 결과값 문자열로 
      public String resultStr()
      {
          String result = "";
          
          result += "사번 : " + empNo + "\n";
          result += "이름 : " + name + "\n";
          result += "부서 : " + part + "\n";
          
          return result;
      }
  }
  
  
  
  public class Manager extends Employee 
  {//extends 부모 클래스 하면 상속 받을 수 있다.
      private String position; // 직책
   
      // 매개변수 4개를 갖는 생성자
      public Manager(String empNo, String name, String part, String position) {
          setEmpNo(empNo);
          setName(name);
          setPart(part);
          this.position = position;
      }
   
      // 관리자에서 추가되는 정보를 결과 문자열로 결합
      public String addStr() {
          String result = "";
          result += "직책 : " + position + "\n";
          return result;
      }
  }
  
  ```

- overriding : 부모의 메소드와 이름,리턴벨류,페러미터들 다 같은 메소드(내용만 다름) => 상송받은 매소드의 재정의

  ```java
  public class Employee{
     
      public String name;
      public int age;
     
      // print() 메소드
      public void print(){
          System.out.println("사원의 이름은 "+this.name+ "이고, 나이는" + this.age+"입니다.");
      }   
  }
  // Employee 상속
  public class Manager extends Employee{
     
      String jobOfManage;
     
      // print() 메소드 오버라이딩
      public void print(){
          System.out.println("사원의 이름은 "+this.name + "이고, 나이는" + this.age + "입니다.");
          System.out.println("관리자 "+this.name+"은 "+this.jobOfManage+" 담당입니다.");
      }
  }
  ```

  - 같은 이름이지만 내용은 다름
  - => 만약 부모 메소드가 abstract라면   무조건 overriding해야만 된다. 

## 인터페이스 

- 인터페이스는 모든 메소드가 abstract이고 부모 메소드의 내용이 없는 클래스라고 보면된다.

- 인터페이스는 여러개 구현해도 된다.

- ```java
  interface I1{
      public void x();
  }
   
  interface I2{
      public void z();
  }
   
  class A implements I1, I2{
      public void x(){}
      public void z(){}   
  }
  ```

- 인터페이스의 멤버는 무조건 public이다. 

##  예외 

- try catch

- ```java
  try{
  
  }catch(Exception e){
  	System.out.println(e.getMessage());
  }
  ```

## 제네릭

- 제네릭(Generic)은 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법

- ```
   
  class Person<T>{
      public T info;
  }
   
  public class GenericDemo {
   
      public static void main(String[] args) {
          Person<String> p1 = new Person<String>();
          Person<StringBuilder> p2 = new Person<StringBuilder>();
      }
   
  }
  ```

- c++ 의 템플렛과 비슷하다.

- 정의할 때는 <T>   , 사용할 때는 person<String>같이 정확한 데이터 타입 입력



- 제네릭 쓰는 이유 => 런타임 에러가 아닌 컴파일 에러를 유도 가능

- ```java
  class StudentInfo{
      public int grade;
      StudentInfo(int grade){ this.grade = grade; }
  }
  class EmployeeInfo{
      public int rank;
      EmployeeInfo(int rank){ this.rank = rank; }
  }
  class Person<T>{
      public T info;
      Person(T info){ this.info = info; }
  }
  public class GenericDemo {
      public static void main(String[] args) {
          Person<EmployeeInfo> p1 = new Person<EmployeeInfo>(new EmployeeInfo(1));
          EmployeeInfo ei1 = p1.info;
          System.out.println(ei1.rank); // 성공
           
          Person<String> p2 = new Person<String>("부장");
          String ei2 = p2.info;
          System.out.println(ei2.rank); // 컴파일 실패
      }
  }
  ```

- 제네릭을 써서 명시적으로 String이 들어갈 것이다라고 해줘서 rank 함수를 보고 컴파일 에러가 나게 한다.=> 런타임 에러는 잡기 어렵기 때문에 최대한 나오지 않게 해주자

- 복수의 제네릭

  - ```java
    class Person<T, S>{
        public T info;
        public S id;
        Person(T info, S id){ 
            this.info = info; 
            this.id = id;
        }
    }
    ```

  - T,S 는 아무 글자나 된다.

- 제네릭은 기본 데이터 타입은 사용하지 못한다. 대신에 Integer()함수등을 이용해서 참조 데이터 타입으로 만들어줘야한다.