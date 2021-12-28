package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class BoardController {

  @RequestMapping("/board/list")
  public Object list() {
    return ArrayList2.toArray(); 
  }

  @RequestMapping("/board/add")
  public Object add(Board board) {
    ArrayList3.add(board);
    return ArrayList2.size;
  }

  @RequestMapping("/board/update")
  public Object update(int index, Board board) {
    if (index < 0 || index >= ArrayList2.size) {
      return 0;
    }

    return ArrayList3.set(index, board) == null ? 0 : 1;
  }

  @RequestMapping("/board/check")
  public Object check(int index, boolean done) {
    if (index < 0 || index >= ArrayList2.size) {
      return 0;  // 인덱스가 무효해서 설정하지 못했다.
    }

    ArrayList2.list[index].done = done;
    return 1; // 해당 항목의 상태를 변경했다.
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) {
    if (index < 0 || index >= ArrayList2.size) {
      return 0;
    }

    ArrayList2.remove(index);
    return 1;
  }
}




