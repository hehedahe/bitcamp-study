package com.eomcs.lang.ex06;

//# 흐름 제어문 - switch와 break

public class Exam0223 {
  public static void main(String[] args) {
    int score = 100;
    // break 문을 쓰지 않으면 계속 이어서 실행된다.
    // => 일부러 쓰지 않을 때가 있다.
    switch (score) {
      case 100:
      case 90:
        System.out.println("A");
        break;
      case 80:
      case 70:
        System.out.println("B");
        break;
      case 60:
      case 50:
      case 40:
        System.out.println("C");
        break;
      default:
        System.out.println("F");
    }
    // => 점수가 10점 단위로 있다는 가정하에 사용 가능
    // 그 외의 숫자는 default로 간주하기 때문에, "90~100점은 A"처럼 복잡한 조건은 if문을 사용해야한다.
  }
}
