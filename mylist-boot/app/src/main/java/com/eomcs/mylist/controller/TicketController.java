package com.eomcs.mylist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

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
    movies[size++] = createCSV(no, name, date, price); // size=movies.length-1
    return size; // movies.length
  };

  @RequestMapping("/my1/get")
  public Object get(String no) {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    return movies[index];
  }

  @RequestMapping("/my1/update")
  public Object update(String no, String name, String date, String price) {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    movies[index] = createCSV(no, name, date, price);
    return 1;
  };

  @RequestMapping("/my1/delete")
  public Object delete(String no) {
    int index = indexOf(no);
    if(index == -1) {
      return 0;
    }
    remove(index);
    return 1;
  };

  String createCSV(String no, String name, String date, String price) {
    return no + "," + name + "," + date + "," + price;
  }

  int indexOf(String no) {
    for (int i = 0; i < size; i++) {
      //String movie = movies[i];
      //String[] values = movie.split(",");
      if (movies[i].split(",")[0].equals(no)) {
        return i;
      }
    }
    return -1;
  }

  String remove(int index) {
    String old = movies[index];
    for (int i = index + 1; i < size; i++) {
      movies[i - 1] = movies[i];
    }
    size--;
    return old;
  }
};
