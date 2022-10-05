# JDBC
Java Database Connectivity

자바 어플리케이션이 DB와 연결되어 데이터를 주고 받을 수 있게 해주는 Java API이다.

추상화된 인터페이스를 통해 DB 종류에 상관없이 각 JDBC 드라이버를 거쳐 특정 DBMS를 사용할 수 있다.
즉, JDBC API를 사용하면 DBMS에 종속되지 않는다.

JDBC는 JDBC Interface와 JDBC Driver로 구성되어 있다.

## JDBC Driver
자바 프로그램의 요청을 DBMS가 이해할 수 있는 프로토콜로 변환해 주는 클라이언트 사이드 어댑터이다.
java.sql 인터페이스들을 상속하여 메서드의 몸체를 구현한 클래스 파일들이다.

## JDBC 보다 Mybatis를 쓰는 이유
JDBC는 한 파일에 SQL + DB 연결 + Java 코드가 반복되기 때문에 재사용성이 좋지 않다.
Mybatis는 SQL문을 자바 코드에서 분리하여 XML파일로 따로 관리한다. SQL문을 애플리케이션 소스코드로부터 분리하여 JDBC를 통해 수동으로 세팅한 파라밈터와 결과를 대신 매핑해 주어 보다 더 간편하게 작업하고 코드량이 줄어 생산성을 높여준다.
