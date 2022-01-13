package com.eomcs.io;

import java.io.FileReader;

// 기존 코드를 자신의 코드인 양 사용하기 위해 해당 클래스를 포함한다.
public class FileReader2 {

  // 기존의 클래스 포함!
  FileReader in;

  public FileReader2(String filename) throws Exception {
    // 기존 객체를 준비한다.
    in = new FileReader(filename);
  }

  public String readLine() throws Exception {
    StringBuilder buf = new StringBuilder();

    int c = in.read();
    while (c != -1) { // 파일에서 한 문자를 읽는다. 더이상 읽을 문자가 없으면 반복문을 종료한다.
      if (c == '\n') {
        return buf.toString();
      } else if (c == '\r') {
        // 무시! CR (Carriage Return; \r) 코드는 버퍼에 담지 말고 버린다.
      } else { // 문자를 읽을 때마다 버퍼에 임시 보관한다.
        buf.append((char)c);
      }
    }
    return buf.toString();
  }


  // 자원해제 하라는 요청이 들어오면 실제 일을 하는 객체에게 전달한다.
  public void close() throws Exception {
    this.close();
  }

}
