package com.eomcs.app2;

import java.net.ServerSocket;
import com.eomcs.app2.handler.ScoreHandler;
import com.eomcs.util.Prompt;

public class ServerApp {

  ScoreHandler scoreHandler = new ScoreHandler(); 

  public static void main(String[] args) {
    new ServerApp().service();
  }


  public void service() {

    try(ServerSocket serverSocket = new ServerSocket(3306);) { // 3306 => mariaDB 포트번호

      while (true) {
        printMenu();
        String input = Prompt.promptString("명령> ");

        if (checkQuit(input)) {
          break;
        }

        try {
          switch (input) {
            case "1": scoreHandler.create(); break;
            case "2": scoreHandler.list(); break;
            case "3": scoreHandler.detail(); break;
            case "4": scoreHandler.update(); break;
            case "5": scoreHandler.delete(); break;
            default:
              System.out.println("올바른 메뉴 번호를 입력하세요!");
          }
        } catch (Exception e) {
          System.out.println("실행 중 오류 발생: " + e.getMessage());
        }
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
    }

    System.out.println("종료!");
  }

}
