// Wrapper 클래스 - 오토박싱(auto-boxing)/오토언박싱(auto-unboxing)
package com.eomcs.basic.ex02;

public class Exam0220 {
  public static void main(String[] args) {

    // 다음과 같이 프로그래밍 중에
    // primitive type의 값을 인스턴스에 담고("박싱(boxing)"이라 부른다)
    // 인스턴스의 담긴 primitive 값을 다시 꺼내는 일("언박싱(unboxing)"이라 부른다)은
    // 매우 불편한다.

    // int ==> Integer
    int i1 = 100;
    Integer obj1 = Integer.valueOf(i1);

    // Integer ==> int
    Integer obj2 = Integer.valueOf(200);
    int i2 = obj2.intValue();

    //    Integer test = 100; // 우변에는 인스턴스 주소가 들어가야하는데 오류가 발생하지 않는 이유?
    // => 컴파일러가 자동으로 100 -> Integer.valueOf(100) 으로 코드를 바꿔준다.
  }
}






