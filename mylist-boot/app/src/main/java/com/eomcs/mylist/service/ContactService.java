package com.eomcs.mylist.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.mylist.domain.Contact;

@Service
public interface ContactService {

  int add(Contact contact);

  List<Contact> list();

  int update(Contact contact);

  int delete(int no);

  Contact get(int no);

}




