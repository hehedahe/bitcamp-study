package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  @RequestMapping("/contact/list")
  public Object list() {
    Contact[] arr = new Contact[ArrayList.size]; // 배열에 저장된 값만 복사할 새 배열을 만든다.
    for (int i = 0; i < ArrayList.size; i++) {
      arr[i]= ArrayList.contacts[i]; // 전체 배열에서 값이 들어있는 항목만 복사한다.
    }
    return arr; // 복사한 항목들을 담고있는 새 배열을 리턴한다.
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    //System.out.println(contact.toString());
    System.out.println(contact); // 인스턴스의 값을 보고싶으면 ?
    System.out.println(contact.email);

    if (ArrayList.size == ArrayList.contacts.length) { // 배열이 꽉 찼다면,
      ArrayList.contacts = ArrayList.grow(); // 메서드 이름에서 해당 코드에 대한 설명을 짐작할 수 있다. => 배열을 늘려라!
    }

    ArrayList.contacts[ArrayList.size++] = contact; // 인스턴스 주소(레퍼런스)가 들어옴

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
    ArrayList.contacts[index] = contact; // 사용자가 새로 입력하여 보낸 데이터 그 자리에 새 값으로 덮어쓰기
    return 1; // 1이면 update됐다!
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