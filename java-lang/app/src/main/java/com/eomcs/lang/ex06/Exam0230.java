package com.eomcs.lang.ex06;

//# 흐름 제어문 - switch 문법

public class Exam0230 {
  public static void main(String[] args) {
    // switch (값) {}
    // 값으로 가능한 데이터 타입은?
    // => int 정수(byte,short,int,char), 문자열, 특별한 상수 Enum 타입
    // => case 값으로 변수를 사용할 수 없다. 리터럴만 가능하다.
    byte b = 2;
    switch (b) {
      case 1:
      case 2:
      default:
    }

    short s = 2;
    switch (s) {
      case 1:
      case 2:
      default:
    }

    int i = 2;
    switch (i) {
      case 1:
      case 2:
      default:
    }

    char c = 'A'; // A 문자에 부여된 유니코드 번호(UTF-16) 0x41(65)을 c에 저장한다.
    // => char c = 65; -> 다른 개발자가 봤을 때, 65가 무슨 문자인지 모르기 때문에 'A'로 적어주는거야
    switch (c) {
      // case 의 값도 int 값이면 무엇이든 된다.
      case 'A': // 0x41 = 65
      case 66: 
      case 0x43: // 8진수, 16진수 사용해도 상관없다.
      default: 
    }

    // String 값을 switch와 case의 값으로 사용할 수 있다.
    String str = "ohora";
    switch (str) {
      case "hello":
      case "ohora":
      case "hul":
      default:
    }

  }
}