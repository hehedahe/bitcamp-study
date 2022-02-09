package com.eomcs.net.ex12.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatClient extends Frame {

  private static final long serialVersionUID = 1L;

  public ChatClient() {
    super("계산기");
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        // 윈도우의 x 버튼을 눌렀을 때
        System.exit(0); // 정상적일 때 0 -> 상태 코드를 리턴
      }
    }); // x 버튼 활성화
    this.setSize(400,300); // 창 크기

    Panel topPanel = new Panel();
    topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 기본 레이아웃은 가운데 정렬이므로 

    TextField addressTf = new TextField(30); // 텍스트를 칠 수 있는 클래스 (like JS input박스)
    topPanel.add(addressTf);

    TextField portTf = new TextField(4);
    topPanel.add(portTf);

    Button connetBtn = new Button("연결");
    topPanel.add(connetBtn);

    add(topPanel, BorderLayout.NORTH);

    TextArea messageListTa = new TextArea(); // 여러줄 입력
    add(messageListTa, BorderLayout.CENTER);

    Panel bottomPanel = new Panel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    TextField messageTf = new TextField(40);
    bottomPanel.add(messageTf);

    Button sendBtn = new Button("보내기");
    bottomPanel.add(sendBtn);

    add(bottomPanel, BorderLayout.SOUTH);



    this.setVisible(true);

  }

  public static void main(String[] args) {
    new ChatClient();
  }
}
