package com.eomcs.mylist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import com.eomcs.mylist.dao.ContactDao;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.mylist.domain.ContactTel;

@Service
// @Component 도 가능하지만! Service 객체를 콕! 집어서 붙일 때는 @Service 애노테이션을 사용한다.
public class ContactServiceTransaction {

  @Autowired
  ContactDao contactDao;

  @Autowired
  TransactionTemplate transactionTemplate;

  public int add(Contact contact) {

    return transactionTemplate.execute(new TransactionCallback<>() {
      @Override
      public Integer doInTransaction(TransactionStatus status) {
        // 트랜잭션으로 묶어서 할 작업을 기술한다.
        contactDao.insert(contact);
        for (ContactTel tel:contact.getTels()) {
          tel.setContactNo(contact.getNo()); // 전화번호 입력 전에 자동 생성된 연락처 번호를 설정한다.
          contactDao.insertTel(tel);
        }
        return 1;       
      }
    });
  }

  public List<Contact> list() {
    List<Contact> contacts = contactDao.findAll();
    for (Contact contact : contacts) {
      contact.setTels(contactDao.findTelByContactNo(contact.getNo()));
    }
    return contacts;
  }

  public Contact get(int no) {
    Contact contact = contactDao.findByNo(no);
    if (contact != null) {
      contact.setTels(contactDao.findTelByContactNo(no));
    }
    return contact;
  }

  public Contact update(Contact contact) {
    int count = contactDao.update(contact);
    if (count > 0) {
      contactDao.deleteTelByContactNo(contact.getNo()); // 전화번호 변경 전에 기존 전화번호를 모두 삭제한다.
      for (ContactTel tel : contact.getTels()) {
        contactDao.insertTel(tel); // 전화번호 객체 안에 이미 연락처 번호가 저장되어 있다.
      }
    }
    return contact;
  }

  public int delete(int no) {
    contactDao.deleteTelByContactNo(no);
    return contactDao.delete(no);
  }

}




