package com.eomcs.net.ex12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.design_pattern.iterator.ArrayList;

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

  public void sendMessage(String message) {
    for (int i = 0; i < clientOutputStreams.size(); i++) {
      DataOutputStream out = (DataOutputStream) clientOutputStreams.get(i);
      try {
        out.writeUTF(message);
      } catch (Exception e) {}
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
            out.writeUTF("Goodbye!");
            out.flush();
            break;
          }
          sendMessage(String.format("[%s] %s", nickname, message));
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
