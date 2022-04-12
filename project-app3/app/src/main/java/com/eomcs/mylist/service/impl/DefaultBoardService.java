package com.eomcs.mylist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.mylist.dao.BoardDao;
import com.eomcs.mylist.domain.Board;
import com.eomcs.mylist.service.BoardService;

@Service
public class DefaultBoardService implements BoardService {

  @Autowired
  BoardDao boardDao;

  @Override
  @Transactional // 다음 메서드는 트랜잭션 안에서 실행하도록 설정한다.
  public int add(Board board) {
    return boardDao.insert(board);
  } 
  // => NonTransaction 클래스 + @Transactional 애노테이션 = 트랜잭션 적용

  @Override
  public List<Board> list() {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) {
    return boardDao.findByNo(no);
  }

  @Override
  @Transactional
  public int update(Board board) {
    return boardDao.update(board);
  }

  @Override
  @Transactional
  public int delete(int no) {
    return boardDao.delete(no);
  }

}



