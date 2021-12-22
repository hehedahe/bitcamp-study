package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  Contact[] contacts = new Contact[5];
  int size = 0; // 배열에 집어넣은 연락처 갯수

  @RequestMapping("/contact/list")
  public Object list() {
    Contact[] arr = new Contact[size]; // 배열에 저장된 값만 복사할 새 배열을 만든다.
    for (int i = 0; i < size; i++) {
      arr[i]= contacts[i]; // 전체 배열에서 값이 들어있는 항목만 복사한다.
    }
    return arr; // 복사한 항목들을 담고있는 새 배열을 리턴한다.
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    //System.out.println(contact.toString());
    System.out.println(contact); // 인스턴스의 값을 보고싶으면 ?
    System.out.println(contact.email);

    if (size == contacts.length) { // 배열이 꽉 찼다면,
      contacts = grow(); // 메서드 이름에서 해당 코드에 대한 설명을 짐작할 수 있다. => 배열을 늘려라!
    }

    contacts[size++] = contact; // 인스턴스 주소(레퍼런스)가 들어옴

    return size;
  }

  @RequestMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return"";
    } //else { // else 생략 가능?
    return contacts[index]; // Contact 인스턴스 리턴 => JSON형식 ?
    //}
  };

  @RequestMapping("contact/update")
  public Object update(Contact contact) {
    int index = indexOf(contact.email);
    if (index == -1) {
      return 0;
    }
    contacts[index] = contact; // 사용자가 새로 입력하여 보낸 데이터 그 자리에 새 값으로 덮어쓰기
    return 1; // 1이면 update됐다!
  };

  @RequestMapping("/contact/delete")
  public Object delete(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return 0;
    }
    remove(index); // → 메서드의 이름으로 코드의 의미를 짐작할 수 있다. ⇒ 이것이 메서드로 분리하는 이유이다.
    return 1;
  };

  // 기능:
  // - 이메일로 연락처 정보를 찾는다.
  // - 찾은 연락처의 배열 인덱스를 리턴한다.
  int indexOf(String email) {
    for (int i = 0; i < size ; i++) {
      Contact contact = contacts[i];
      if (contact.email.equals(email)) {  // 예) "u1@test.com"
        return i;
      }
    }
    return -1;
  };

  // 기능:
  // - 배열에서 저장한 항목을 삭제한다.
  Contact remove(int index) {
    Contact old = contacts[index];
    // 현재 위치의 다음 항목에서 배열 끝까지 반복하며 앞으로 값을 당겨온다.
    // 뒤에 있는 칭구들을 앞으로 땡겨와야하니 index는 삭제하는 다음 인덱스 기준 => index+1
    for (int i = index + 1; i < size ; i++) {
      contacts[i - 1] = contacts[i];
    }
    size--;
    return old;
  }

  // 기능:
  // - 배열의 크기를 늘린다.
  // - 기존 배열의 값을 복사해온다.
  Contact[] grow() {
    // 기존 배열보다 50% 큰 배열을 새로 만든다.
    //int newCapacity = newLength();
    //String[] arr = new String[newCapacity];
    Contact[] arr = new Contact[newLength()];
    copy(contacts, arr);
    return arr; // 메모리 위치정보인 배열 주소가 리턴
  }

  // 기능:
  // - 주어진 배열의 길이를 50% 증가시킨 새 배열의 길이를 알려준다.
  int newLength() {
    return contacts.length + (contacts.length >> 1); // 오른쪽으로 1비트 이동 = contact.length / 2
  }

  // 기능:
  // - 배열을 복사한다.
  void copy(Contact[] source, Contact[] target) { // void는 return값이 없음 // Contact 배열 => Contact 레퍼런스 배열
    // 개발자가 잘못 사용할 것을 대비해서 다음 코드를 추가한다.
    // 즉, target 배열이 source 배열보다 작을 경우 target 배열 크기만큼만 복사한다. 
    int length = source.length;
    if (target.length < source.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) { // target 배열이 더 작을 경우, target 배열만큼만 돌리겠다
      target[i] = source[i]; 
    }
  } // if문 true라면 기존에 갖고 있던 연락처가 일부 삭제되므로 옳은 코드는 아니다!
  // 다만 메서드를 만들 때, 예외가 발생하는 경우가 있는데 그걸 처리하기 위해?
}