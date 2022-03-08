package com.eomcs.mylist.dao;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.eomcs.mylist.domain.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository  
public class JsonBookDao extends AbstractBookDao {

  String filename = "books.json";

  public JsonBookDao() {
    try {
      ObjectMapper mapper = new ObjectMapper();

      // JSON데이터 -> Collection 타입으로 변환
      bookList.addAll(Arrays.asList(mapper.readValue(new File(filename), Book[].class))); // JSON데이터 -> Book[] -> List타입
      bookList.addAll(mapper.readValue(new File(filename), mapper.getTypeFactory().constructCollectionType(List.class, Book.class))); // JSON데이터 -> List타입
      bookList.addAll(mapper.readValue(new File(filename), new TypeReference<List<Book>>(){})); // JSON데이터 -> List타입



    } catch (Exception e) {
      System.out.println("독서록 데이터 로딩 중 오류 발생!");
    }
  }

  @Override
  protected void save() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(filename), bookList.toArray());
  }
}











