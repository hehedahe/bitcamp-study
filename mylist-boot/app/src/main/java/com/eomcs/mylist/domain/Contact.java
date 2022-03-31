package com.eomcs.mylist.domain;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Contact {

  int contactNo;
  String name;
  String email;
  String company;
  List<ContactTel> tels; 
  // => Contact 클래스에 ContactTel 클래스를 참조하는 코드가 없다면 
  //    ContactTel 클래스에 "Contact contact;" 를 넣어 단방향 참조해준다.

}
