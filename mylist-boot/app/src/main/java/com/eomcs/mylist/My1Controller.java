package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My1Controller {

  String[] movies = new String[10];
  int size = 0;

  @RequestMapping("/my1/list")
  public Object list() {
    String[] records = new String[size];
    for (int i = 0; i < size; i++) {
      records[i] = movies[i];
    }
    return records;
  };

  @RequestMapping("/my1/add")
  public Object add(String no, String name, String date, String price) {
    String movie = no + "," + name + "," + date + "," + price;
    movies[size++] = movie; // size=movies.length-1
    return size; // movies.length
  };

  @RequestMapping("/my1/get")
  public Object get(String no) {
    for (int i = 0; i < size; i++) {
      //String movie = movies[i];
      //String[] values = movie.split(",");
      if (movies[i].split(",")[0].equals(no)) {
        return movies[i];
      }
    }
    return "";
  }

  @RequestMapping("/my1/update")
  public Object update(String no, String name, String date, String price) {
    String movie = no + "," + name + "," + date + "," + price;
    for (int i = 0; i < size; i++) {
      if (movies[i].split(",")[0].equals(no)) {
        movies[i] = movie;
        return 1;
      }
    }
    return 0;
  };

  @RequestMapping("/my1/delete")
  public Object delete(String no) {
    for (int i = 0; i < size; i++) {
      if (movies[i].split(",")[0].equals(no)) {
        for (int j = i + 1; j < size; j++) {
          movies[j-1] = movies[j];
        }
        size--;
        return 1;
      }
    }
    return 0;
  };
}
