package com.eomcs.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Book;
import com.eomcs.utility.ArrayList;

@RestController 
public class BookController {

  ArrayList bookList;

  public BookController() throws Exception {
    bookList  = new ArrayList();
    System.out.println("BookController() 호출됨!");

    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("books.data")));

    while (true) { // readLine()이 null을 리턴한다면 더이상 읽을 데이터가 없다는 뜻!
      try {
        Book book = new Book();
        book.setTitle(in.readUTF());
        book.setAuthor(in.readUTF());
        book.setPress(in.readUTF());
        book.setPage(in.readInt());
        book.setPrice(in.readInt());
        String date = in.readUTF();
        if (date.length() > 0) {
          book.setReadDate(Date.valueOf(date));
        }
        //        book.setReadDate(Date.valueOf(in.readUTF()));
        book.setFeed(in.readUTF());

        bookList.add(book);

      } catch (Exception e) {
        break;
      }
    }
    in.close();
  }

  @RequestMapping("/book/list")
  public Object list() {
    return bookList.toArray(); 
  }

  @RequestMapping("/book/add")
  public Object add(Book book) throws Exception {
    bookList.add(book);
    save();
    return bookList.size();
  }

  @RequestMapping("/book/get")
  public Object get(int index) throws Exception {
    if (index < 0 || index >= bookList.size()) {
      return "";
    }
    save();
    return bookList.get(index);
  };

  @RequestMapping("/book/update")
  public Object update(int index, Book book) throws Exception {
    if (index < 0 || index >= bookList.size()) {
      return 0; // update 안됐으면 0
    }
    Book old = (Book)bookList.get(index);
    save();
    return bookList.set(index, book) == null ? 0 : 1;
  }

  @RequestMapping("/book/delete")
  public Object delete(int index) throws Exception {
    if (index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.remove(index) == null ? 0 : 1;
  }

  @RequestMapping("/book/save")
  public Object save() throws Exception {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("books.data")));

    Object[] arr = bookList.toArray();
    for(Object obj : arr) {
      Book book = (Book) obj;
      out.writeUTF(book.getTitle());
      out.writeUTF(book.getAuthor());
      out.writeUTF(book.getPress());
      out.writeInt(book.getPage());
      out.writeInt(book.getPrice());
      if (book.getReadDate() == null) {
        out.writeUTF("");
      } else {
        out.writeUTF(book.getReadDate().toString());
      }
      out.writeUTF(book.getFeed());
    }

    out.close();
    return arr.length;
  }

}
