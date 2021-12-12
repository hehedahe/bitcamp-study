package com.eomcs.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 클라이언트 요청을 처리하는 역할
public class ContactController {

  // 인스턴스 변수: 클래스에 선언한 변수
  // => 모든 인스턴스 메서드가 공유한다.
  String[] contacts = new String[10];
  int size = 0; // index번호는 0부터 시작

  @GetMapping("/contact/list")
  public Object list() {
    String[] records = new String[size];
    for (int i =0; i < size; i++) {
      records[i] = contacts[i]; // add()에서 추가된 배열을 새 배열 records에 담아줘!
    };
    return records;
  };

  @GetMapping("/contact/add")
  public Object add(String name, String email, String tel, String company) {
    contacts[size++] = name + "," + email + "," + tel + "," + company; // size번방에 문자열을 넣음
    //int temp = size;
    //size = size +1;
    //contacts[temp] = ~;

    return size; // 그 다음 방인 size+1번방을 리턴
  };

  //클라이언트가 요청한 이메일 정보에 대한 상세정보 가져오기
  @GetMapping("/contact/get")
  public Object get(String email) {
    // 배열 전체 반복이 아니라 배열에 입력된 갯수만큼만 반복해야해
    for (int i = 0; i < size; i++) {
      if (email.equals(contacts[i].split(",")[1])) { // Java에서는 ==로 문자열 비교 못함 -> equals() 메서드 사용
        return contacts[i];
      }
    }
    return "";
  };
};