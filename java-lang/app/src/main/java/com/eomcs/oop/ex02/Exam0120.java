package com.eomcs.oop.ex02;

// # 사용자 정의 데이터 타입 + 클래스 메서드
//
public class Exam0120 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;

    // 메서드를 이용하여 이 타입의 데이터를 다룰 수 있는 연산자를 정의한다.
    // - 사용자 정의 데이터 타입 입장에서는 메서드가 연산자 역할을 한다.
    // - 즉 사용자 정의 데이터 타입에 메서드를 정의하는 것은
    //   그 데이터를 다룰 연산자를 정의하는 것이다.

    // Score 데이터 값을 다룰 수 있는 새 연산자를 정의해 보자.
    // - 다음 메서드는 Score 객체의 국,영,수 값의 합계와 평균을 계산하는 연산자이다.
    public static void calculate(Score score) {
      score.sum = score.kor + score.eng + score.math;
      score.average = score.sum / 3f;
    }
    // 클래스 메서드
    // - static이 붙은 메서드이다.
    // - 특정 인스턴스에 대해 사용하는 것이 아니라, 모든 인스턴스에 대해 사용할 수 있다.
    // - 특정 인스턴스의 값을 다루고 싶다면 파라미터로 그 인스턴스의 주소를 받아야 한다.
  }

  public static void main(String[] args) {

    Score s1 = new Score();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;

    // 다음은 Score의 값을 다루는 연산자가 없을 때의 예이다.
    //    s.sum = s.kor + s.eng + s.math; 
    //    s.average = s.sum / 3f;

    Score s2 = new Score();
    s2.name = "채다해";
    s2.kor = 10;
    s2.eng = 20;
    s2.math = 30;

    // 사용자 정의 데이터 타입의 값을 연산자를 사용하여 다뤄보자!
    Score.calculate(s1);
    Score.calculate(s2);

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", s1.name, s1.kor, s1.eng, s1.math, s1.sum, s1.average);
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", s2.name, s2.kor, s2.eng, s2.math, s2.sum, s2.average);
  }
}

