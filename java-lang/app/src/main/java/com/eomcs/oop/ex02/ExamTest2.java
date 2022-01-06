package com.eomcs.oop.ex02;

// 1) 관련 메서드를 클래스로 묶는다.
// 2) 메서드에서 계산한 결과를 클래스 변수에 저장한다.
// 3) 인스턴스 변수로 바꿔서 결과를 개별적을 관리한다.
// 4) 인스턴스 메서드로 바꿔서 인스턴스 주소를 this 변수에 받는다.
// 5) 클래스를 별도의 소스 파일로 분리한다.
// 6) 클래스를 패키지로 분류한다.
public class ExamTest2 {

  static class Calculator {

    int result = 0;

    void plus(int value) {
      this.result += value;
    }

    void minus(int value) {
      this.result -= value;
    }

    void multiple(int value) {
      this.result *= value;
    }

    void divide(int value) {
      this.result /= value;
    }

  }

  public static void main(String[] args) {
    // 2 + 3 - 1 * 7 / 3 = ?

    Calculator c = new Calculator();

    c.plus(2);
    c.plus(3);
    c.minus(1);
    c.multiple(7);
    c.divide(3);

    System.out.printf("result = %d\n", c.result);
  }
}