// inner class : 클래스 정의와 인스턴스 생성
package com.eomcs.oop.ex11.c;

class X {} // Top Level Class

class A {

  class X { // inner class (= non-static nested class) ≠ static nested class는 아니야!
    // 컴파일러는 inner 클래스를 컴파일 할 때 다음과 같이 
    // - 바깥 클래스의 인스턴스 주소를 저장할 필드를 추가하고,
    // - 바깥 클래스의 인스턴스 주소를 파라미터로 받는 생성자를 만든다.
    //
    //    A outer;
    //    public X(A obj) {
    //      this.outer = obj;
    //    }
    // => static 을 떼면 위 소스를 컴파일러가 자동으로 추가해준다.
  }

  static class Y {} // 바깥 클래스의 주소가 필요 없다!
}

public class Exam0110 {

  public static void main(String[] args) {
    // 레퍼런스 선언
    A.X obj;
    A.Y obj2;

    // 인스턴스 생성
    //    obj = new A.X(); // 컴파일 오류! 바깥 클래스의 인스턴스 주소 없이 생성 불가!
    obj2 = new A.Y();

    //1) 바깥 클래스의 인스턴스 준비
    //    A  outer = null; // NullPointerException: Cannot invoke "Object.getClass()" because "outer" is null
    A  outer = new A();

    //2) 바깥 클래스의 인스턴스 주소를 사용하여 inner class의 인스턴스 생성
    obj = outer.new X();

    // 컴파일러는 컴파일 할 때 다음과 같이 
    // 바깥 클래스의 객체를 생성자에 전달하는 코드로 변경한다.
    //    obj = new A.X(outer);
    // 컴파일러가 변경할 코드를 개발자가 직접 작성하는 것이 아니다.

  }

}
