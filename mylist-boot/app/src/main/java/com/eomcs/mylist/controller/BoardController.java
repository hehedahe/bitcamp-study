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
    // 어떤 메서드를 호출할 때 인스턴스 주소를 주면서 호출해야 한다.
    // boardDao 변수에 들어있는 인스턴스 주소로 가서, 그 안에 있는 변수들을 사용해서 insert() 메서드를 사용해
    // 그 변수 = boardList 변수
    return boardDao.countAll();
  }

  @RequestMapping("/board/get")
  public Object get(int index) throws Exception {
    Board board = boardDao.findByNo(index);
    if (board == null) {
      return "";
    }
    boardDao.increaseViewCount(index);
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
