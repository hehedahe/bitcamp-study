package com.eomcs.net.ex12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class ChatServer {

  int port;

  ArrayList clientOutputStreams = new ArrayList();

  public ChatServer(int port) {
    this.port = port;
  }

  public void service() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        new RequestHandler(serverSocket.accept()).start();
      }

    } catch (Exception e) {
      System.out.println("서버 실행 오류 - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public void sendMessage(String message) {
    ArrayList deleteStreams = new ArrayList(); // 삭제 명단

    for (int i = 0; i < clientOutputStreams.size(); i++) {
      DataOutputStream out = (DataOutputStream) clientOutputStreams.get(i); // 전체 목록이 clientOutputStreams에 들어있다.
      try {
        out.writeUTF(message);
      } catch (Exception e) {
        System.out.println("전송 오류: " + message);
        //        clientOutputStreams.remove(i);
        deleteStreams.add(out); // 무효한 출력 스트림은 삭제 명단에 등록한다.
      }
    }

    // 삭제 명단에 등록된 출력 스트림을 클라이언트 목록에서 제거한다.
    for (Object delestreams : deleteStreams) {
      clientOutputStreams.remove(delestreams);
    }
  }

  class RequestHandler extends Thread {
    Socket socket;

    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
      try (Socket socket2 = socket;
          DataOutputStream out = new DataOutputStream(socket2.getOutputStream());
          DataInputStream in = new DataInputStream(socket2.getInputStream())) {

        clientOutputStreams.add(out);

        String nickname = in.readUTF();

        out.writeUTF(nickname + "님, 환영합니다!");
        out.flush();

        while (true) {
          String message = in.readUTF();
          if (message.equals("\\quit")) {
            out.writeUTF("<![QUIT[]>"); // 연결을 끊겠다는 특별한 메세지를 클라이언트에게 보낸다.
            out.flush();
            clientOutputStreams.remove(out); // 메세지 출력 목록에서 연결이 종료된 클라이언트를 제거한다.
            break;
          }
          sendMessage(String.format("[%s]  %s", nickname, message));
          //          sendMessage("[" + nickname + "]" + message); // 위 코드와 동일
        }

      } catch (Exception e) {
        System.out.println("클라이언트와의 통신 오류! - " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    new ChatServer(8888).service();
  }

}
