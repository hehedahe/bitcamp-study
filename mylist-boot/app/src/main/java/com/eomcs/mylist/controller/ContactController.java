package com.eomcs.mylist.controller;

import java.io.FileReader;
import java.io.FileWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.utility.ArrayList;

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

    FileReader in = new FileReader("contacts.csv");

    StringBuilder buf = new StringBuilder();

    int c;
    while ((c = in.read()) != -1) { // 파일에서 한 문자를 읽는다.
      if (c == '\n') { // 만약 읽은 문자가 줄바꿈 명령이라면, 지금까지 읽은 CSV 데이터를 분석하여 Contact 객체에 담는다.
        contactList.add(Contact.valueOf(buf.toString())); // 파일에서 읽은 CSV 데이터로 객체를 초기화 시킨 후 목록에 추가한다.
        buf.setLength(0); // 다음 데이터를 읽기 위해 버퍼를 초기화시킨다.

      } else { // 문자를 읽을 때마다 버퍼에 임시 보관한다. 
        buf.append((char) c);
      }
    }
    in.close();
  }


  @RequestMapping("/contact/list")
  public Object list() { // 클라이언트 요청을 다루는 메서드
    return contactList.toArray(); // 배열을 다루는 메서드를 리턴
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    //System.out.println(contact); // 인스턴스의 값을 보고싶으면 ?
    contactList.add(contact);
    return contactList.size();
  }

  @RequestMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return"";
    } //else { // else 생략 가능?
    return contactList.get(index); // Contact 인스턴스 리턴 => JSON형식 ?
    //}
  };

  @RequestMapping("contact/update")
  public Object update(Contact contact) {
    int index = indexOf(contact.getEmail());
    if (index == -1) {
      return 0;
    }
    return contactList.set(index, contact) == null ? 0 : 1;
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return 0;
    }
    contactList.remove(index); // → 메서드의 이름으로 코드의 의미를 짐작할 수 있다. ⇒ 이것이 메서드로 분리하는 이유이다.
    return 1;
  };

  @RequestMapping("/contact/save")
  public Object save() throws Exception {
    FileWriter out = new FileWriter("contacts.csv"); // 따로 경로를 지정하지 않으면 파일은 프로젝트 폴더에 생성된다.

    Object[] arr = contactList.toArray();
    for(Object obj : arr) {
      Contact contact = (Contact) obj;
      out.write(contact.toCsvString() + "\n");
    }

    out.close();
    return arr.length;
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