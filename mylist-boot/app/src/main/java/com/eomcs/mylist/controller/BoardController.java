package com.eomcs.mylist.controller;

import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.dao.CsvBoardDao;
import com.eomcs.mylist.domain.Board;

@RestController
public class BoardController {

  CsvBoardDao boardDao = new CsvBoardDao(); // 게시글 보관 처리 역할

  @RequestMapping("/board/list")
  public Object list() {
    return boardDao.findAll();
  }

  @RequestMapping("/board/add")
  public Object add(Board board) throws Exception {
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    boardDao.insert(board);
    save();
    return boardDao.countAll();
  }

  @RequestMapping("/board/get")
  public Object get(int index) throws Exception {
    Board board = boardDao.findByNo(index);
    if (board == null) {
      return "";
    }
    board.setViewCount(board.getViewCount() + 1);
    save();
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

    save();

    return boardDao.update(index, board);
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) throws Exception {
    save();
    return boardDao.delete(index);
  }

  @RequestMapping("/board/save")
  public Object save() throws Exception {
    boardDao.save();
    return boardDao.countAll();
  }
}
