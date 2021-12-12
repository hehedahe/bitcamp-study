package com.eomcs.mylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class test {

  @GetMapping("/test/list")
  public Object list() {
    String[] contacts = {
        "홍길동11, hong2@test.com, 010-2222-3333, 비트",
        "홍길동22, hong2@test.com, 010-2222-3333, 캠프",
        "홍길동33, hong2@test.com, 010-2222-3333, 멀어요!"
    };
    return contacts;
  };

  @GetMapping("/test/add")
  public Object add(String name, String email, String tel, String company) {
    String contact = name + "," + email + "," + tel + "," + company;
    return contact;
  };
};