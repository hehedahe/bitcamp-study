// static nested class : 클래스 정의와 인스턴스 생성
package com.eomcs.oop.ex11.b;

class A {
  // 일반적인 스태틱 변수, 스태틱 메서드처럼 스태틱 클래스도 똑같이 생각하자!
  static int a;
  static void m() {}

  static class X {

  }
}

public class Exam0110 {

  public static void main(String[] args) {
    A.a = 100;
    A.m();

    // 레퍼런스 선언
    A.X obj;

    // 인스턴스 생성
    obj = new A.X();
  }

}
