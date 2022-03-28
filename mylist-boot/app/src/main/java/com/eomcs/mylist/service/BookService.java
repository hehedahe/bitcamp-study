package com.eomcs.mylist.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.mylist.domain.Book;

@Service
public interface BookService {

  int add(Book book);

  List<Book> list();

  int update(Book book);

  int delete(int no);

  Book get(int no);

}




