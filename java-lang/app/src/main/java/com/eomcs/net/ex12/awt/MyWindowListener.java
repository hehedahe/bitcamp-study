package com.eomcs.net.ex12.awt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// 이 클래스는 ChatClient의 main()에서만 사용할 수 있습니다.

public class MyWindowListener extends WindowAdapter {
  @Override
  public void windowClosing(WindowEvent e) {
    // 윈도우의 x 버튼을 눌렀을 때
    System.exit(0); // 정상적일 때 0 -> 상태 코드를 리턴
  }
}
