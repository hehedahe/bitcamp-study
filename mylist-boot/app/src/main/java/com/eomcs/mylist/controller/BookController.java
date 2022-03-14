package com.eomcs.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.dao.BookDao;
import com.eomcs.mylist.domain.Book;

@RestController 
public class BookController {

  @Autowired
  BookDao bookDao;

  @RequestMapping("/book/list")
  public Object list() {
    return bookDao.findAll(); 
  }

  @RequestMapping("/book/add")
  public Object add(Book book) {
    return bookDao.insert(book);
  }

  @RequestMapping("/book/get")
  public Object get(int no) {
    Book book = bookDao.findByNo(no);
    return book != null ? book : "";
  };

  @RequestMapping("/book/update")
  public Object update(Book book) {
    return bookDao.update(book);
  }

  @RequestMapping("/book/delete")
  public Object delete(int no) {
    return bookDao.delete(no);
  }


}
