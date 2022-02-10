package com.eomcs.net.ex12.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ChatClient extends JFrame {

  private static final long serialVersionUID = 1L;

  Socket socket;
  DataInputStream in;
  DataOutputStream out;
  String nickname; // 인스턴스 필드가 새로 만들어질 때 null로 초기화 되므로 String nickname = null; 로 작성하지 않아도 된다.

  // 컴파일하면 아래 문장들은 variable initialize => 변수 선언문은 남고, 변수 값 할당하는 문장은 생성자 안으로 들어간다.
  JTextField addressTf = new JTextField(30); // 텍스트를 칠 수 있는 클래스 (like JS input박스)
  JTextField portTf = new JTextField(4);
  JButton connectBtn = new JButton("연결");

  JTextArea messageListTa = new JTextArea(); // 여러줄 입력
  JTextField messageTf = new JTextField(35);


  public ChatClient() {

    String title = "대화명을 입력하세요.\n(2자 이상)";

    while (true) {
      nickname = JOptionPane.showInputDialog(title);
      if (nickname == null) {
        System.exit(0); // 시스템 종료
      } else if (nickname != null && nickname.length() >= 2) {
        break;
      }
      title = "대화명을 다시 입력하세요!\n(2자 이상)";
    }

    this.setTitle("채팅!! - " + nickname);

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) { // 윈도우의 x 버튼을 눌렀을 때
        if (connectBtn.getText().equals("종료")) {
          try {
            out.writeUTF("\\quit");
            out.flush();
          } catch (Exception ex) { // connectChatServer의 파라미터인 ActionEvent e 와 구분하기 위해 ex로 하기
          }
        }
        try {in.close();} catch (Exception ex) {}
        try {out.close();} catch (Exception ex) {}
        try {socket.close();} catch (Exception ex) {}
        System.exit(0); // 정상적일 때 0 -> 상태 코드를 리턴
      }
    }); // x 버튼 활성화
    this.setSize(500,400); // 창 크기

    Container contentPane = this.getContentPane();
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 기본 레이아웃 디폴트 = 가운데 정렬 => 기본 레이아웃 관리자를 교체
    topPanel.add(addressTf);
    topPanel.add(portTf);

    portTf.addActionListener(this::connectChatServer); // port No. 입력 후 엔터 -> connetChatServer()

    // 1) 로컬 클래스
    //    class MyActionListener implements ActionListener {
    //      @Override
    //      public void actionPerformed(ActionEvent e) {
    //      }
    //    }
    //    connetBtn.addActionListener(new MyActionLIstener());

    // 2) 익명 클래스
    //    connetBtn.addActionListener(new ActionListener() {
    //      @Override
    //      public void actionPerformed(ActionEvent e) {
    //      }
    //    });

    // 3) 람다(lambda) 문법
    //    connetBtn.addActionListener(e -> System.out.println("연결 버튼 눌렀음!"));

    // 4) 메서드 레퍼런스
    connectBtn.addActionListener(this::connectChatServer);
    topPanel.add(connectBtn);
    contentPane.add(topPanel, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(messageListTa);
    contentPane.add(scrollPane, BorderLayout.CENTER);


    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    bottomPanel.add(messageTf);
    JButton sendBtn = new JButton("보내기");
    sendBtn.addActionListener(this::sendMessage);
    bottomPanel.add(sendBtn);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);

    messageTf.addActionListener(this::sendMessage);

    this.setVisible(true);
  }

  public static void main(String[] args) throws Exception {
    //    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    //    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); // 리눅스 OS 만 가능
    //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows OS 만 가능
    //    UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel"); // macOS 만 가능
    new ChatClient();
  }

  public void connectChatServer(ActionEvent e) {

    if (connectBtn.getText().equals("연결")) {

      //    System.out.println("서버에 연결하기!");
      //    System.out.println(addressTf.getText());
      //    System.out.println(portTf.getText());

      try {
        socket = new Socket(
            addressTf.getText(), 
            Integer.parseInt(portTf.getText()));

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(nickname);
        out.flush();

        new MessageReceiver(in).start();

      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "서버 연결 오류!", "오류!", JOptionPane.ERROR_MESSAGE);
      }

      connectBtn.setText("종료"); // 서버에 연결되면 연결 버튼 -> 종료 버튼 바뀜
    } else {
      try {
        out.writeUTF("\\quit");
        out.flush();
      } catch (Exception ex) { // connectChatServer의 파라미터인 ActionEvent e 와 구분하기 위해 ex로 하기
      }
      connectBtn.setText("연결");
      messageListTa.setText("");
    }
  }
  public void sendMessage(ActionEvent e) {
    if (messageTf.getText().length() == 0) {
      return;
    }

    try {
      out.writeUTF(messageTf.getText());
      out.flush();
      messageTf.setText("");

    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "메세지 전송 오류!", "통신 오류!", JOptionPane.ERROR_MESSAGE);
    }
  }

  class MessageReceiver extends Thread {
    DataInputStream in;

    public MessageReceiver(DataInputStream in) {
      this.in = in;
    }

    @Override
    public void run() {
      while (true) {
        try {
          String message = in.readUTF();
          if (message.equals("<![QUIT[]>")) { // 서버에서 연결을 끊겠다는 메세지가 오면 스레드를 종료한다.
            break; // 스레드 종료? run() 메서드의 실행을 마치면 스레드는 종료한다.
          }
          messageListTa.append(message + "\n");
        } catch (Exception e) {}
      }
    }
  }
}
