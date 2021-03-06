package com.eomcs.lang.ex01;
/*
# 클래스 이름과 소스 파일 이름 II
- 클래스를 공개하는 경우(public), 소스 파일 이름은 반드시 클래스 이름과 같아야 한다.

다음과 같이 클래스명과 소스 파일명이 다르면 컴파일 오류가 발생한다.
 */

//public class Exam2_2x {}

/*
 * ## 실습 
 * 1) 컴파일하기 
 * - $ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/lang/ex01/Exam2_2.java 
 * → 컴파일 오류가 발생
 * 
 * - Err 내용
 * src\main\java\com\eomcs\lang\ex01\Exam0220.java:7: error: class Exam2_2x is public, 
 * should be declared in a file named Exam2_2x.java
 * 
 * 2) class 명을 Exam0220 으로 변경하면 컴파일 가능
 */
