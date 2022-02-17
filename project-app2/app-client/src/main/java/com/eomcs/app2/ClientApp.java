package com.eomcs.app2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.eomcs.app2.handler.ScoreHandler;
import com.eomcs.app2.table.ScoreTable;
import com.eomcs.app2.vo.Score;

public class ClientApp {

  ScoreHandler scoreHandler = new ScoreTable(); 

  public static void main(String[] args) {
    new ClientApp().service();
  }


  public void service() {


    try(Socket socket = new Socket("localhost", 3306);) {


      while (true) {
        Socket socket = serverSocket.accept();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
          String command = in.readUTF();
          if (command.equals("qhuit")) {
            break;
          }
          try{
            switch (command) {
              case "insert":
                Score score = (Score) in.readObject();
                int count = ScoreTable.insert(score);
                out.writeUTF("success");
                out.writeInt(count);
                break;
              case "selectList":
                Score[] scores = ScoreTable.selectList();
                out.writeUTF("success");
                out.writeObject(scores);
                break;
              case "selectOne":
                int no = in.readInt();
                score = ScoreTable.selectOne(no);
                out.writeUTF("success");
                out.writeObject(score);
                break;
              case "update":
                no = in.readInt();
                score = (Score) in.readObject(); 
                count = ScoreTable.update(no, score);
                out.writeUTF("success");
                out.writeInt(count);
                break;
              case "delete":
                no = in.readInt();
                count = ScoreTable.delete(no);
                out.writeUTF("success");
                out.writeInt(count);
                break;
              default:
                out.writeUTF("fail");
                out.writeUTF("해당 명령을 지원하지 않습니다.");
                break;
            }
          } catch (Exception e) {
            out.writeUTF("fail");
            out.writeUTF("실행 오류: " + e.getMessage());
          }

        }
      }
    } catch (Exception e) {
      System.out.println("서버와 통신하는 중 오류 발생: " + e.getMessage());
    }

    System.out.println("종료!");
  }

}
