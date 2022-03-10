package com.eomcs.mylist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.eomcs.mylist.domain.Board;

// @Repository
// - 클래스에 이 애노테이션을 붙여 표시해 두면, Spring Boot가 실행될 때 이 클래스의 객체를 자동 생성한다.
// - 또한 이 객체를 원하는 곳에 자동으로 주입한다.
//
// ** @Component 과 @Repository 모두 객체를 자동 생성하는 애노테이션!
//    Spring에서 DAO 처리하는 객체는 @Repository 로 사용한다.
// ** 클라이언트의 요청을 받아 처리하는 애노테이션은 @RestController 
@Repository
public class JdbcBoardDao implements BoardDao {

  public JdbcBoardDao() {
    System.out.println("JdbcBoardDao 객체 생성!");
  }

  @Override
  public int countAll() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement( //
            "select board_no,title,created_date,view_count from ml_board order by board_no desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> arr = new ArrayList<>();
      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setCreatedDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));
        arr.add(board);
      }
      return arr;
    }
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection( //
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt =
            con.prepareStatement("insert into ml_board(title,content) values(?,?)");) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());

      return stmt.executeUpdate();
    }
  }

  @Override
  public Board findByNo(int no) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update(int no, Board board) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void increaseViewCount(int no) throws Exception {
    // TODO Auto-generated method stub

  }


}











