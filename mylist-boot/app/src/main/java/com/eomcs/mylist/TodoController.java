package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  // Todo 객체 목록을 저장할 메모리를 준비한다.
  ArrayList todoList = new ArrayList();

  @RequestMapping("/todo/list")
  public Object list() {
    return ArrayList.toArray(todoList);
  }

  @RequestMapping("/todo/add")
  public Object add(int index, Todo todo) {
    ArrayList.add(todoList, todo);
    return todoList.size;
  }

  @RequestMapping("/todo/get")
  public Object get(int index) {
    if (index < 0 || index >= todoList.size) {
      return "";
    }
    return todoList.list[index];
  };

  @RequestMapping("todo/update")
  public Object update(int index, boolean done) {
    if (index < 0 || index >= todoList.size) {
      return 0;
    }
    ((Todo) todoList.list[index]).done = done;
    return ArrayList.set(todoList, index, done) == null ? 0 : 1;
  };

  @RequestMapping("/todo/delete")
  public Object delete(int index) {
    if (index < 0 || index >= todoList.size) {
      return 0;
    }
    ArrayList.remove(todoList, index);
    return 1;
  };
}