package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  String[] contacts = new String[5];
  int size = 0; // 배열에 집어넣은 연락처 갯수

  @RequestMapping("/contact/list")
  public Object list() {
    String[] arr = new String[size]; // 배열에 저장된 값만 복사할 새 배열을 만든다.
    for (int i = 0; i < size; i++) {
      arr[i]= contacts[i]; // 전체 배열에서 값이 들어있는 항목만 복사한다.
    }
    return arr; // 복사한 항목들을 담고있는 새 배열을 리턴한다.
  }

  @RequestMapping("/contact/add")
  public Object add(String name, String email, String tel, String company) {
    String contact = name + "," + email + "," + tel + "," + company;
    contacts[size++] = contact;
    return size;
  }

  @RequestMapping("/contact/get")
  public Object get(String email) {
    for (int i = 0; i < size ; i++) {
      // 학습용
      //String contact = contacts[i]; // "u1,u1@test.com,1111,비트캠프"
      //String[] values = contact.split(","); // {"u1", "u1@test.com", "1111", "비트캠프"}
      //if (values[1].equals(email)) { // "u1@test.com"
      //  return contacts[i];
      //}
      // 현업에서 사용하는 방식
      if (contacts[i].split(",")[1].equals(email)) {  // 예) "u1@test.com"
        return contacts[i];
      }
    }
    return "";
  };

  @RequestMapping("contact/update")
  public Object update(String name, String email, String tel, String company) {
    String contact = name + "," + email + "," + tel + "," + company; // 사용자가 새로 입력하여 보낸 데이터
    for (int i = 0; i < size ; i++) {
      if (contacts[i].split(",")[1].equals(email)) { // 새 데이터(문자열)을 어디에 저장할지? email과 같은 문자열 찾기
        contacts[i] = contact; // 그 자리에 새 값으로 덮어쓰기
        return 1; // 1이면 update됐다!
      } 
    }
    return 0; //반복문을 다 돌았는데 같은 문자열을 못 찾았을 때 리턴 (다른 사용자가 이미 값을 변경,삭제 했을 경우 등)
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) {
    for (int i = 0; i < size ; i++) {
      if (contacts[i].split(",")[1].equals(email)) { // 예) "u1@test.com"
        // 현재 위치의 다음 항목에서 배열 끝까지 반복하며 앞으로 값을 당겨온다.
        // 뒤에 있는 칭구들을 앞으로 땡겨와야하니 j는 삭제하는 다음 인덱스 기준 => i+1
        for (int j = i + 1; j < size ; j++) {
          contacts[j -1] = contacts[j];
        }
        size--;
        return 1;
      } 
    }
    return 0;
  };
};
