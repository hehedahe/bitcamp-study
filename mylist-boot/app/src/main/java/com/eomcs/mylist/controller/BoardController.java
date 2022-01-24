package com.eomcs.mylist.controller;

import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.dao.CsvBoardDao;
import com.eomcs.mylist.domain.Board;

@RestController
public class BoardController {

  CsvBoardDao boardDao; // 게시글 보관 처리 역할

  public BoardController() {
    System.out.println("BoardController() 호출됨!");

    try {
      boardDao = new CsvBoardDao();
    } catch (Exception e) {
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
    }
  }

  @RequestMapping("/board/list")
  public Object list() {
    return boardList.toArray();
  }

  @RequestMapping("/board/add")
  public Object add(Board board) throws Exception {

    board.setCreatedDate(new Date(System.currentTimeMillis()));
    boardList.add(board);
    save();
    return boardList.size();
  }


  @RequestMapping("/board/get")
  public Object get(int index) throws Exception {
    if (index < 0 || index >= boardList.size()) {
      return "";
    }
    Board board = (Board) boardList.get(index);
    board.setViewCount(board.getViewCount() + 1);

    save();
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(int index, Board board) throws Exception {
    if (index < 0 || index >= boardList.size()) {
      return 0;
    }

    Board old = (Board) boardList.get(index);
    board.setViewCount(old.getViewCount());
    board.setCreatedDate(old.getCreatedDate());

    save();

    return boardList.set(index, board) == null ? 0 : 1;
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) throws Exception {
    if (index < 0 || index >= boardList.size()) {
      return 0;
    }
    save();
    return boardList.remove(index) == null ? 0 : 1;
  }

  @RequestMapping("/board/save")
  public Object save() throws Exception {
    boardDao.save();
    return boardDao.countAll();
  }
}
