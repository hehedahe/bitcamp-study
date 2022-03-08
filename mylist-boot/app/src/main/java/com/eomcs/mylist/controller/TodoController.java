package com.eomcs.mylist.controller;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Todo;

@RestController
public class TodoController {

  // Todo 객체 목록을 저장할 메모리를 준비한다.
  ArrayList todoList = new ArrayList();


  public TodoController() {
    System.out.println("TodoController() 호출됨!");
  }


  @RequestMapping("/todo/list")
  public Object list() {
    return todoList.toArray();
  }

  @RequestMapping("/todo/add")
  public Object add(int index, Todo todo) {
    todoList.add(todo);
    return todoList.size();
  }

  @RequestMapping("/todo/get")
  public Object get(int index) {
    if (index < 0 || index >= todoList.size()) {
      return "";
    }
    return todoList.get(index);
  };

  @RequestMapping("todo/update")
  public Object update(int index, Todo todo) {
    if (index < 0 || index >= todoList.size()) {
      return 0;
    }
    Todo old = (Todo) todoList.get(index);
    todo.setDone(old.isDone()); // 기존의 체크 정보를 그대로 가져가야 한다.
    return todoList.set(index, todo) == null ? 0 : 1;
  };

  @RequestMapping("/todo/delete")
  public Object delete(int index) {
    if (index < 0 || index >= todoList.size()) {
      return 0;
    }
    todoList.remove(index);
    return 1;
  };
}