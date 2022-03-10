package com.eomcs.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.dao.BoardDao;
import com.eomcs.mylist.domain.Board;

@RestController
public class BoardController {

  // @Autowired
  // 
  // 
  @Autowired/*(required = false)*/
  BoardDao boardDao;

  @RequestMapping("/board/list")
  public Object list() throws Exception {
    return boardDao.findAll();
  }

  @RequestMapping("/board/add")
  public Object add(Board board) throws Exception {
    return boardDao.insert(board);
  }

  @RequestMapping("/board/get")
  public Object get(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if (board == null) {
      return "";
    }
    boardDao.updateViewCount(no);
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(int index, Board board) throws Exception {
    Board old = boardDao.findByNo(index);
    if (old == null) {
      return 0;
    }

    board.setViewCount(old.getViewCount());
    board.setCreatedDate(old.getCreatedDate());

    return boardDao.update(index, board);
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) throws Exception {
    return boardDao.delete(index);
  }

}
