package com.eomcs.mylist.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  // Contact 객체 목록을 저장할 메모리 준비
  // => Object[] list = new Object[5];
  // => int size = 0;
  ArrayList contactList;

  public ContactController() throws Exception {
    contactList  = new ArrayList();
    System.out.println("ContactController() 호출됨!");

    try {
      BufferedReader in = new BufferedReader(new FileReader("contacts.json"));

      ObjectMapper mapper = new ObjectMapper();
      Contact[] contacts = mapper.readValue(in.readLine(), Contact[].class);

      for(Contact contact : contacts) {
        contactList.add(contact);
      }
      in.close();
    } catch (Exception e) {
      System.out.println("연락처 데이터를 로딩하는 중에 오류 발생!");
    }
  }


  @RequestMapping("/contact/list")
  public Object list() { // 클라이언트 요청을 다루는 메서드
    return contactList.toArray(); // 배열을 다루는 메서드를 리턴
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) throws Exception {
    //System.out.println(contact); // 인스턴스의 값을 보고싶으면 ?
    contactList.add(contact);
    save();
    return contactList.size();
  }

  @RequestMapping("/contact/get")
  public Object get(String email) throws Exception {
    int index = indexOf(email);
    if (index == -1) {
      return"";
    } //else { // else 생략 가능?
    return contactList.get(index); // Contact 인스턴스 리턴 => JSON형식 ?
    //}
  };

  @RequestMapping("contact/update")
  public Object update(Contact contact) throws Exception{
    int index = indexOf(contact.getEmail());
    if (index == -1) {
      return 0;
    }
    save();
    return contactList.set(index, contact) == null ? 0 : 1;
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) throws Exception {
    int index = indexOf(email);
    if (index == -1) {
      return 0;
    }
    contactList.remove(index); // → 메서드의 이름으로 코드의 의미를 짐작할 수 있다. ⇒ 이것이 메서드로 분리하는 이유이다.
    save();
    return 1;
  };

  @RequestMapping("/contact/save")
  public Object save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.json")));

    ObjectMapper mapper = new ObjectMapper(); 
    out.println(mapper.writeValueAsString(contactList.toArray()));

    out.close();
    return contactList.size();
  }

  // 기능:
  // - 이메일로 연락처 정보를 찾는다.
  // - 찾은 연락처의 배열 인덱스를 리턴한다.
  int indexOf(String email) {
    for (int i = 0; i < contactList.size(); i++) {
      Contact contact = (Contact) contactList.get(i);
      if (contact.getEmail().equals(email)) {  // 예) "u1@test.com"
        return i;
      }
    }
    return -1;
  }
}