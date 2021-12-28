package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class BoardController {

  @RequestMapping("/board/list")
  public Object list() {
    return ArrayList3.toArray(); 
  }

  @RequestMapping("/board/add")
  public Object add(Board board) {
    ArrayList3.add(board);
    return ArrayList3.size;
  }

  @RequestMapping("/board/get")
  public Object get(int index) {
    if (index == -1) {
      return"";
    }
    return ArrayList3.list[index];
  };

  @RequestMapping("/board/update")
  public Object update(int index, Board board) {
    if (index < 0 || index >= ArrayList3.size) {
      return 0;
    }
    return ArrayList3.set(index, board) == null ? 0 : 1;
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) {
    if (index < 0 || index >= ArrayList3.size) {
      return 0;
    }
    ArrayList3.remove(index);
    return 1;
  }

}
