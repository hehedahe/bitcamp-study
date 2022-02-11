// anonymous class : 인터페이스를 구현한 익명 클래스 정의
package com.eomcs.oop.ex11.e;

public class Exam0110 {
  // 인터페이스의 경우, static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  // => 인터페이스는 규칙을 정의한 것이기 때문에 인스턴스 멤버라는 개념이 존재하지 않는다.
  interface A {
    void print();
  }

  class X {}
  static class Y {}

  public static void main(final String[] args) {

    Y r1 = new Y(); // 스태틱 메서드 안에서 스태틱 클래스를 사용 가능
    X r2; // 인스턴스의 레퍼런스는 선언 가능
    //    r2 = new X(); // 인스턴스 주소 없이 논스태틱 클래스 사용 불가

    // 1) 로컬 클래스로 인터페이스 구현하기
    class My implements A {
      String name = "홍길동"; 

      @Override
      public void print() {
        System.out.printf("Hello, %s!\n", this.name);
      }
    }

    // My obj = new My(); 도 되지만,
    A obj = new My(); // A 인터페이스 
    obj.print();
  }
}