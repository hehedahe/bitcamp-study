package com.eomcs.mylist.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.mylist.domain.ContactTel;
import com.eomcs.mylist.service.ContactService;

@RestController
// 이 클래스가 클라이언트 요청 처리 담당자임을 표시한다.
// 이 표시(애노테이션)가 붙어 있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  @Autowired
  ContactService contactService; // 클래스 대신 인터페이스를 지정한다.

  @RequestMapping("/contact/list")
  public Object list() { // 클라이언트 요청을 다루는 메서드
    return contactService.list();
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact, String[] tel) {

    // 요청 파라미터 분석 및 가공
    ArrayList<ContactTel> telList = new ArrayList<>();
    for (int i = 0; i < tel.length; i++) {
      String[] value = tel[i].split("_"); // 데이터 분석
      if (value[1].length() == 0) {
        continue;
      }
      ContactTel contactTel = new ContactTel(Integer.parseInt(value[0]), value[1]);
      telList.add(contactTel); // 데이터 가공
    }
    contact.setTels(telList);

    // 서비스 객체 실행
    return contactService.add(contact);

    /*
    // 1) 트랜잭션으로 묶어서 실행할 작업을 정의
    //    => 스프링 프레임워크에서 정한 규칙에 따라 정의해야 한다. => TransactionCallback
    class ContactAddTransaction implements TransactionCallback {
      @Override
      public Object doInTransaction(TransactionStatus status) {
        // 트랜잭션으로 묶어서 할 작업을 기술한다.

      }
    }

    // 2) 트랜잭션 작업을 수행한다.
    return transactionTemplate.execute(new ContactAddTransaction());
     */
  }

  @RequestMapping("/contact/get")
  public Object get(int no) {
    Contact contact = contactService.get(no);
    if (contact == null) {
      return ""; // 컨트롤러는 서비스 객체의 리턴 값에 따라 응답 데이터(JSON)를 적절히 가공하여 리턴한다. 
    }
    return contact;
  };

  @RequestMapping("contact/update")
  public Object update(Contact contact, String[] tel) throws Exception{
    // 요청 파라미터 분석 및 가공
    ArrayList<ContactTel> telList = new ArrayList<>();

    for (int i = 0; i < tel.length; i++) {
      String[] value = tel[i].split("_"); // 데이터 분석
      if (value[1].length() == 0) {
        continue;
      }
      // 연락처 변경의 경우, 이미 연락처 번호를 알기 때문에
      // 전화번호를 객체에 담을 때 연락처 번호도 함께 저장한다. => add()와 다른 점
      ContactTel contactTel = new ContactTel(contact.getContactNo(), Integer.parseInt(value[0]), value[1]);
      telList.add(contactTel); // 데이터 가공
    }
    contact.setTels(telList);

    // 서비스 객체 실행
    return contactService.update(contact);
  };

  @RequestMapping("/contact/delete")
  public int delete(int no) throws Exception {
    return contactService.delete(no);
  };

}