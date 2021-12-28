package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  @RequestMapping("/contact/list")
  public Object list() { // 클라이언트 요청을 다루는 메서드
    return ArrayList.toArray(); // 배열을 다루는 메서드를 리턴
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    //System.out.println(contact); // 인스턴스의 값을 보고싶으면 ?
    ArrayList.add(contact);
    return ArrayList.size;
  }

  @RequestMapping("/contact/get")
  public Object get(String email) {
    int index = ArrayList.indexOf(email);
    if (index == -1) {
      return"";
    } //else { // else 생략 가능?
    return ArrayList.contacts[index]; // Contact 인스턴스 리턴 => JSON형식 ?
    //}
  };

  @RequestMapping("contact/update")
  public Object update(Contact contact) {
    int index = ArrayList.indexOf(contact.email);
    if (index == -1) {
      return 0;
    }
    return ArrayList.set(index, contact) == null ? 0 : 1;
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) {
    int index = ArrayList.indexOf(email);
    if (index == -1) {
      return 0;
    }
    ArrayList.remove(index); // → 메서드의 이름으로 코드의 의미를 짐작할 수 있다. ⇒ 이것이 메서드로 분리하는 이유이다.
    return 1;
  };






}