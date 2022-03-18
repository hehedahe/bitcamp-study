package com.eomcs.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.dao.ContactDao;
import com.eomcs.mylist.domain.Contact;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  @Autowired
  ContactDao contactDao;



  @RequestMapping("/contact/list")
  public Object list() { // 클라이언트 요청을 다루는 메서드
    return contactDao.findAll(); // 배열을 다루는 메서드를 리턴
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact, String[] tel) {
    System.out.println(contact);
    for (String t : tel) {
      System.out.println(t + ",");
    }
    System.out.println();

    //    contactDao.insert(contact);

    return 0;
  }

  @RequestMapping("/contact/get")
  public Object get(int no) {
    Contact contact = contactDao.findByNo(no);
    return contact != null ? contact : "";
  };
  /*
  @RequestMapping("contact/update")
  public Object update(Contact contact) throws Exception{
    int index = indexOf(contact.getEmail());
    if (index == -1) {
      return 0;
    }
    save();
    return contactDao.set(index, contact) == null ? 0 : 1;
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) throws Exception {
    int index = indexOf(email);
    if (index == -1) {
      return 0;
    }
    contactDao.remove(index); // → 메서드의 이름으로 코드의 의미를 짐작할 수 있다. ⇒ 이것이 메서드로 분리하는 이유이다.
    save();
    return 1;
  };

  @RequestMapping("/contact/save")
  public Object save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.json")));

    ObjectMapper mapper = new ObjectMapper(); 
    out.println(mapper.writeValueAsString(contactDao.toArray()));

    out.close();
    return contactDao.size();
  }

  // 기능:
  // - 이메일로 연락처 정보를 찾는다.
  // - 찾은 연락처의 배열 인덱스를 리턴한다.
  int indexOf(String email) {
    for (int i = 0; i < contactDao.size(); i++) {
      Contact contact = (Contact) contactDao.get(i);
      if (contact.getEmail().equals(email)) {  // 예) "u1@test.com"
        return i;
      }
    }
    return -1;
  }
   */
}