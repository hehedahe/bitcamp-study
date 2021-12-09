package com.eomcs.lang.ex06;

//# 흐름 제어문 - break와 continue 활용

public class Exam0321 {
  public static void main(String[] args) {
    int count;
    int sum;


    // 1부터 100까지의 짝수의 합은?
    // => continue 사용 전
    count = 0;
    sum = 0;
    while (count < 100) {
      count++;
      if ((count & 1) == 0) { // -> count의 bit값 마지막 자리를 추출 => if 짝수면 아래를 실행해라!
        // count & 1 = count & 0x01 = count % 2
        sum += count;
      }
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);

    System.out.println("------------------------");

    // => continue 사용 후
    count = 0;
    sum = 0;
    while (count < 100) {
      count++;
      if (count % 2 == 1) // if 홀수라면
        continue; // 다음 문장을 실행하지 않고 즉시 조건문(count < 100)으로 이동한다.
      sum += count;
    }
    System.out.printf("count=%d, sum=%d\n", count, sum);
  }
}
