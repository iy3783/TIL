# aspectjrt  

> joinpoint , pointcut , advise 만 알면된다.

- aop : 관점지향 프로그래밍

  - 기능을 핵심 비지니스 로직과 공통 모듈로 구분하고 핵심로직에 영향 미치지 않고 사이사이에 공통 모듈을 효과적으로 잘 끼워넣는 개발 방법

  - 공통 모듈(보안인증 , 로깅 같은 요소들)을 만든 후에 코드 밖에서 이 모듈을 비지니스 로직에 삽입 하는 것이 바로 AOP적인 개발(코드 밖에서 설정이 된다.)

  - 사용되는곳 

    1. 간단한 메소드 성능 검사(쿼리문 속도 측정등)
    2. 트랜잭션 처리 : 트랜잭션은 비지니스 로직의 전후에 설정된다.  하지만 매번 사용하는 트랜잭션(try-catch 부분)의 코드는 번거롭고 복잡하다.
    3. 예외 반환
    4. 아키텍쳐 검증
    5. 멀티 쓰레드 safety 관련해서  메소드들에 일괄적으로 락을 설정하는 애스팩트

  - AOP 구성요소

    - joinPoint : 횡단 관심 모듈의 기능이 삽입되어 동작할 수 있는 실행 가능한 특정 위치

      - ex) 메소드가 호출되는 부분 또는 리턴되는 시점 , 필드를 액세스 하는 부분, 인스턴스가 만들어지는 지점 , 등등이 조인 포인트가 될 수 있다. 각각의 조인포인트들은 그 안에 횡단 관심의 기능이 AOP에 의해 자동으로 추가 되어져서 동작할 수 있는 후보지가 되는 것이다.

    - pointCut :  어떤 클래스의 어느 조인 포인트를 사용할 것인지를 결정하는 선택기능

      - 사용할 모듈의 특정 조인포인트를 지정한다. aspectj 에서는 와일드 카드를 이용한 메소드 시그니처를 사용한다.

    - advise 또는 intercepter 

      - advise는 각 조인포인트에 삽입되어져 동작할 수 있는 코드

      - intercepter는 인터셉터 체인 방식의 AOP 툴에서 사용하는 용어로 주로 한 개의 invoke 메소드를 가지는 어드바이스

      - 어드바이스의 종류

        ```
        Before advice : 메서드 실행전에 적용되는 실행
        After returning advice : 메서드가 정상적으로 실행된 후에 실행
        After throwing advice : 예외를 발생시킬 때 적용되는 Advice를 정의 (catch와 비슷)
        Around advice : 메서드 호출 이전, 이후, 예외 발생 등 모든 시점에서 적용 가능한 Advice를 정의
        ```

      - aspect 란 : pointcut(어디에서) + advise (무엇을 할것인지)

  - aop는 xml로 하든지  어노테이션으로 하든지 2가지 방법이 있다.
