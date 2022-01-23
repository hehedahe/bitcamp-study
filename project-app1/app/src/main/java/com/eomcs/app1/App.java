package com.eomcs.app1;

import java.util.Arrays;
import java.util.Scanner;

public class App {
  // 2) main() 밖에 선언하면 main()와 prompt()에서 공유할 수 있음
  // => 그냥 Scanner keyScan ~ 은 인스턴스 멤버라 main()에서 직접 접근X -> static으로 변경
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {

    while (true) {
      Command command = prompt();

      if (command.getName().equals("exit") || command.getName().equals("quit")) {
        break;
      } else if(command.getName().equals("")) {
        continue; 

      } else if(command.getName().equals("help")) {
        doHelp();

      } else if (command.getName().equals("add")) {
        doAdd(command);

      } else if (command.getName().equals("minus")) {
        doMinus(command);

      } else {
        System.out.println("지원하지 않는 연산자입니다.");
      }
    }

    keyScan.close();
  }

  // 1) main()에서 직접 호출해야 하니 static으로 만들기
  static Command prompt() {
    System.out.print("> ");
    String[] values = keyScan.nextLine().split(" "); // add 100 200

    return new Command(values[0], Arrays.copyOfRange(values, 1, values.length));
  }

  // 3) doHelp()
  static void doHelp() {
    System.out.print(
        "add   [값1] [값2]  더하기 계산을 수행한다.\n"
            + "minus [값1] [값2]  빼기 계산을 수행한다.\n"
            + "help               도움말을 출력한다.\n");
  }

  // 4) doAdd()
  static void doAdd(Command command) {
    if (command.getParamSize() != 2) {
      System.out.println("add 명령어 입력 형식이 옳바르지 않습니다.");
      System.out.println("형식: add 값1 값2");
      System.out.println("예) add 100 200");
    } else {
      int v1 = command.getInt(0);
      int v2 = command.getInt(1);
      System.out.printf("%d + %d = %d\n", v1, v2, (v1 + v2));
    }
  }

  // 5) doMinus()
  static void doMinus(Command command) {
    if (command.getParamSize() != 2) {
      System.out.println("minus 명령어 입력 형식이 옳바르지 않습니다.");
      System.out.println("형식: minus 값1 값2");
      System.out.println("예) minus 100 200");
    } else {
      int v1 = command.getInt(0);
      int v2 = command.getInt(1);
      System.out.printf("%d - %d = %d\n", v1, v2, (v1 - v2));
    }
  }
}
