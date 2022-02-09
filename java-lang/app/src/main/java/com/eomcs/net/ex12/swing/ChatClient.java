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

  JTextField addressTf = new JTextField(30); // 텍스트를 칠 수 있는 클래스 (like JS input박스)
  JTextField portTf = new JTextField(4);
  JTextArea messageListTa = new JTextArea(); // 여러줄 입력
  JTextField messageTf = new JTextField(35);


  public ChatClient() {
    super("**채팅**");
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        // 윈도우의 x 버튼을 눌렀을 때
        try {in.close();} catch (Exception ex) {}
        try {out.close();} catch (Exception ex) {}
        try {socket.close();} catch (Exception ex) {} // 정상적일 때 0 -> 상태 코드를 리턴
        System.exit(0);
      }
    }); // x 버튼 활성화
    this.setSize(500,400); // 창 크기

    Container contentPane = this.getContentPane();
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 기본 레이아웃 디폴트 = 가운데 정렬 => 기본 레이아웃 관리자를 교체
    topPanel.add(addressTf);
    topPanel.add(portTf);
    JButton connetBtn = new JButton("연결");

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
    connetBtn.addActionListener(this::connectChatServer);
    topPanel.add(connetBtn);
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
    System.out.println("서버에 연결하기!");
    //    System.out.println(addressTf.getText());
    //    System.out.println(portTf.getText());

    try {
      socket = new Socket(
          addressTf.getText(), 
          Integer.parseInt(portTf.getText()));

      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      new MessageReceiver(in).start();

    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "서버 연결 오류!", "오류!", JOptionPane.ERROR_MESSAGE);
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
          messageListTa.append(message + "\n");
        } catch (Exception e) {}
      }
    }
  }
}
