package com.eomcs.lang.ex06;

//# 흐름 제어문 - do ~ while 반복문

public class Exam0340 {
  public static void main(String[] args) {
    int i = 0;

    // 1부터 10까지 출력하기
    do
      System.out.println(++i); //먼저 실행하고 
    while (i < 10);  //조건 체크해서 참이면 다시 반복

    System.out.println("---------------------");

    // 여러 개의 문장을 반복할 때는 블록으로 묶어라!
    i = 0;
    do {
      i += 1;
      System.out.println(i);
    } while (i < 10);
  }
}

/*
# do ~ while
- 최소 한 번은 반복한다.
- 한 번 이상 반복하면 do ~ while
- 0 번 이상 반복하면 while => 조건 먼저 검사하기 때문에 조건이 false이면 아예 반복안 할 수 있음!

  do
    문장1;
  while (조건);

  do {
    문장1;
    문장2;
    문장3;
  } while (조건);
 */
