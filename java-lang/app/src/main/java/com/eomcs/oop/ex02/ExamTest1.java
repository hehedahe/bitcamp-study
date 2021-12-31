package com.eomcs.oop.ex02;

// 1) 사용자 정의 데이터 타입을 만든다.
// 2) 인스턴스 값을 다룰 calculate() 연산자를 스태틱 메서드로 만든다.
// 3) calculate() 연산자를 논스태틱 메서드로 만든다.
public class ExamTest1 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;

    public void calculate() {
      this.sum = this.kor + this.eng + this.math;
      this.average = this.sum / 3f;
    }
  }

  public static void main(String[] args) {
    Score s = new Score();

    s.name = "홍길동";
    s.kor = 100;
    s.eng = 90;
    s.math = 85;

    s.calculate();

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math,
        s.sum, s.average);
  }
}