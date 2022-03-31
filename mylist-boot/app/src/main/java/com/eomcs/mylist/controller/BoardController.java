package com.eomcs.mylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.service.BoardService;

@RestController
public class BoardController {

  // @Autowired
  // - 필드 선언부에 이 애노테이션을 붙여서 표시해두면,
  //   Spring Boot가 BoardController 객체를 만들 때 BoardDao 구현체를 찾아 자동으로 주입한다.
  // 
  @Autowired/*(required = false)*/
  BoardService boardService;
  /*
  @RequestMapping("/board/list")
  public Object list() {
    return boardService.findAll();
  }

  @RequestMapping("/board/add")
  public Object add(Board board, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    if (member == null) {
      return new ResultMap().setStatus(FAIL).setData("로그인 하지 않았습니다.");
    }
    board.setWriter(member.getNo());
    boardService.add(board);
    return new ResultMap().setStatus(SUCCESS) ;
  }

  @RequestMapping("/board/get")
  public Object get(int no) {
    Board board = boardService.findByNo(no);
    if (board == null) {
      return "";
    }
    boardService.updateViewCount(no);
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(Board board, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    if (member == null) {
      return new ResultMap().setStatus(FAIL).setData("로그인 하지 않았습니다.");
    }

    board.setWriter(member.getNo());
    int count = boardService.update(board);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/board/delete")
  public Object delete(int no, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    if (member == null) {
      return new ResultMap().setStatus(FAIL).setData("로그인 하지 않았습니다.");
    }

    Board board = new Board();
    board.setNo(no);
    board.setWriter(member.getNo());

    int count = boardService.delete(no);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
    return boardService.delete(no);
  }
   */
}
