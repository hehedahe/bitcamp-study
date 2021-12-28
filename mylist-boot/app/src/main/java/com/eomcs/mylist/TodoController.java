package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  @RequestMapping("/todo/list")
  public Object list() {
    return ArrayList2.toArray();
  }

  @RequestMapping("/todo/add")
  public Object add(int index, Todo todo) {
    ArrayList2.add(todo);
    return ArrayList2.size;
  }

  @RequestMapping("/todo/get")
  public Object get(int index) {
    if (index < 0 || index >= ArrayList2.size) {
      return "";
    }
    return ArrayList2.list[index];
  };

  @RequestMapping("todo/update")
  public Object update(int index, Todo todo) {
    if (index < 0 || index >= ArrayList2.size) {
      return 0;
    }
    return ArrayList2.set(index, todo) == null ? 0 : 1;
  };

  @RequestMapping("/todo/delete")
  public Object delete(int index) {
    if (index < 0 || index >= ArrayList2.size) {
      return 0;
    }
    ArrayList2.remove(index);
    return 1;
  };
}