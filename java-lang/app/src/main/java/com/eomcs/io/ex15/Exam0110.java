// 바이너리 파일을 텍스트 형식으로 저장하기
package com.eomcs.io.ex15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;  // Base64 클래스 안에 있는 중첩클래스 Encoder

public class Exam0110 {
  public static void main(String[] args) throws Exception{
    Encoder encoder = Base64.getEncoder();

    File file = new File("./temp/photo.jfif"); // 파일에 대한 정보가 필요할 때 사용
    FileInputStream in = new FileInputStream(file);
    FileWriter out = new FileWriter("./temp/photo.txt");

    byte[] buf = new byte[(int)file.length()]; // length() 리턴값은 long 타입 => int로 형변환 
    int len = in.read(buf);
    System.out.printf("읽은 바이트 수: %d\n", len);

    // 바이트 배열에 저장된 바이너리 데이터를 텍스트로 변환하기
    String encodedStr = encoder.encodeToString(Arrays.copyOf(buf,len));
    //      System.out.println(encodedStr);

    // 텍스트로 변환된 데이터를 
    out.write(encodedStr);

    in.close();
    out.close();
  }
}
