package com.eomcs.lang.ex07;

import java.util.Scanner;

//# 메서드 : 사용 전
//
public class Exam0110Test {

  static void printSpaces(int len) { // len = length 줄임말로 자주 쓰임
    int spaceCnt = 1;
    while (spaceCnt <= len) {
      System.out.print(" ");
      spaceCnt++;
    }
  }

  static void printStars(int len) {
    int starCnt = 1;
    while (starCnt <= len) {
      System.out.print("*");
      starCnt++;
    }
  }

  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("밑변의 길이? ");
    int len = keyScan.nextInt();
    keyScan.close();

    int starLen = 1;
    while (starLen <= len) {
      printSpaces((len - starLen) / 2); // 공백을 몇 개 출력할지 계산식(expression) 표현식을 파라미터로 줌
      printStars(starLen);

      // 출력 줄 바꾸기
      System.out.println();
      starLen += 2;
    }
  }
}