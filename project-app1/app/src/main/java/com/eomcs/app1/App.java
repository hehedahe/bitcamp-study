package com.eomcs.app1;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    while (true) {
      System.out.print("> ");
      String input = keyScan.nextLine();
      if (input.equals("exit") || input.equals("quit")) {
        break;
      } else if(input.length() == 0) {
        continue;
      } else if(input.equals("help")) {
        System.out.print(
            "add   [값1] [값2]  더하기 계산을 수행한다.\n"
                + "minus [값1] [값2]  빼기 계산을 수행한다.\n"
                + "help               도움말을 출력한다.\n");
      } else {
        String[] values = input.split(" ");
        if (values[0].equals("add")) {
          if (values.length != 3) {
            System.out.println("명령어 입력 형식이 옳지 않습니다.");
          } else {
            int v1 = Integer.parseInt(values[1]);
            int v2 = Integer.parseInt(values[2]);
            System.out.printf("%d + %d = %d\n", v1, v2, (v1 + v2));
          }
        } else if (values[0].equals("minus")) {
          if (values.length != 3) {
            System.out.println("명령어 입력 형식이 옳지 않습니다.");
          } else {
            int v1 = Integer.parseInt(values[1]);
            int v2 = Integer.parseInt(values[2]);
            System.out.printf("%d - %d = %d\n", v1, v2, (v1 - v2));
          }
        } else {
          //          System.out.println(input);
          System.out.println("지원하지 않는 연산자입니다.");
        }
      }
    }

    keyScan.close();
  }
}
