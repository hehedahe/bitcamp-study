package com.eomcs.lang.ex03;

//# 정수 리터럴1 
//- 다양한 기수법으로 정수를 표현하기

public class Exam0210 {
  public static void main(String[] args) {
    //# 10진수 리터럴 
    //- 코드에서 일반적으로 정수 값을 표현할 때 사용한다.
    System.out.println(100);

    //8진수 리터럴
    //- 코드를 작성할 때 잘 사용하지 않는다.
    //- 0으로 시작해야 한다.
    System.out.println(0144);

    //2진수 리터럴
    //- 메모리의 상태를 직설적으로 보여주고 싶을 때 사용한다.
    //- 0b 또는 0B로 시작한다.
    System.out.println(0b1100100);
    System.out.println(0B1100100);

    //- 숫자 앞에 0이 있어도 된다.
    System.out.println(0b0110_0100);
    System.out.println(0B0110_0100);
    // 보기 편하게 _를 이용해도 출력되는 값은 똑같다!

    //16진수 리터럴
    //- 2진수를 간결하게 표현하기 위해 사용한다.
    //  2진수를 4자리씩 끊어서 계산한 숫자로 표현 = 16진수
    //- 0x 또는 0X 로 시작한다.
    System.out.println(0x64);
    System.out.println(0X64);

    //- 숫자 앞에 0이 있어도 된다.
    System.out.println(0x064);
    System.out.println(0X064);
  }
}