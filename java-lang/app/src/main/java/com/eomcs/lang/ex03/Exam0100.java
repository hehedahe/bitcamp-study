package com.eomcs.lang.ex03;

//# 리터럴(literal)
//- 자바 언어로 표현한 값.


public class Exam0100 {
  public static void main(String[] args) {
    System.out.println("-------- 정수 리터럴");
    System.out.println(78); // 10진수
    System.out.println(+78);
    System.out.println(-78);
    System.out.println(0116); // 8진수
    // 맨 앞에 0 빼고 1*8² + 1*8¹ + 6*8^0 = 78
    System.out.println(0x4e); // 16진수
    // 맨 앞에 0x 빼고 4*16¹ + e*16^0 = 78
    // 16진수: 1,2,3,4,5,6,7,8,9,a,b,c,d,e,f
    System.out.println(0b01001110); // 2진수
    // 맨 앞에 0b 빼고 1*2^6 + 1*2³ + 1*2² + 1*2¹ = 78

    System.out.println("-------- 부동소수점 리터럴");
    System.out.println(3.14);
    System.out.println(31.4e-1);
    // e = 10 → e-1 = 10^-1
    // => 31.4 * (1/10) = 3.14
    System.out.println("-------- 논리 리터럴");
    System.out.println(true);
    System.out.println(false);

    System.out.println("-------- 문자 리터럴");
    System.out.println('가');

    System.out.println("-------- 문자열 리터럴");
    System.out.println("오호라 코딩스쿨!");
  }
}