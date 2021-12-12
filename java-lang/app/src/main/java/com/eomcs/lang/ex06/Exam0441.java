package com.eomcs.lang.ex06;

// # 흐름 제어문 - for(;;) 와 배열
// for문과 배열이 만나면 시작 조건, 종료 조건, 증가치를 조정할 수 있는 장점이 있다!

public class Exam0441 {
  public static void main(String[] args) {
    String[] names = {"홍길동", "임꺽정", "유관순", "윤봉길", "안중근"};

    // 인덱스 범위 조정
    for (int i = 2; i < 4; i++) {
      System.out.println(names[i]);
    }

  }
}