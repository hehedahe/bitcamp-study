package com.eomcs.net.ex11.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

  public static void main(String[] args) {

    try (ServerSocket ss = new ServerSocket(8888);){
      System.out.println("계산기 서버에 오신 걸 환영합니다!");
      try (Socket socket = ss.accept();
          BufferedReader in = new BufferedReader(new InputStream(socket.getInputStream()))
          ){

      } catch (Exception e) {
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
