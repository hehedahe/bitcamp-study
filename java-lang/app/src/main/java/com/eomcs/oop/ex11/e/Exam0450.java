// anonymous class - 익명 클래스가 놓이는 장소: 파라미터
package com.eomcs.oop.ex11.e;

// Top level class
class My {
  static void m1() {
    System.out.println("오호라!!!!");
  }

  void m2() {
    System.out.println("와우~~~~~!");
  }
}

public class Exam0450 {

  // 추상메서드가 단 한 개인 인터페이스 = functional interface
  interface A {
    void print();
    default void test() {}
  }

  static A create1() {
    class X implements A {
      @Override
      public void print() {
        System.out.println("Hello!");
      }
    }
    return new X();
  }

  // 익명 클래스
  static A create2() {
    return new A() {
      @Override
      public void print() {
        System.out.println("Hello2!");
      }
    };
  }

  // 메서드 1개만 갖고 있는 인터페이스 = functional interface
  // => 람다식으로 표현할 수 있다.
  static A create3() {
    return () -> System.out.println("Hello3!");
  }

  static A create4() {
    return My::m1;
  }

  static A create5() {
    return new My()::m2;
  }

  public static void main(String[] args) {
    A obj1 = create1();
    obj1.print();

    A obj2 = create2();
    obj2.print();

    A obj3 = create3();
    obj3.print();

    A obj4 = create4();
    obj4.print();

    A obj5 = create5();
    obj5.print();
  }


}