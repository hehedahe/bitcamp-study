package com.eomcs.app2;

import java.util.Scanner;

public class App {
  Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    //    App app = new App();
    //    app.service();
    new App().service();
  }


  public void service() {

    while (true) {
      printMenu();

      String input = prompt();

      if (checkQuit(input)) {
        break;
      }

      switch (input) {
        case "1":
          break;
        case "2":
          break;
        case "3":
          break;
        case "4":
          break;
        case "5":
          break;

        default:
          System.out.println("올바른 메뉴 번호를 입력하세요!");
      }
    }

    System.out.println("종료!");
    keyScan.close();
  }

  public void printMenu() {
    System.out.println("메뉴:");
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 상세");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyScan.nextLine(); 
  }

  private boolean checkQuit(String input) {
    return input.equals("quit") || input.equals("exit");
  }
}
