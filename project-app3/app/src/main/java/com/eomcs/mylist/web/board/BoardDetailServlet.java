package com.eomcs.mylist.web.board;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 컨테이너가 실행할 클래스를 만드려면
// Servlet API 규칙에 따라 작성해야 한다.
//
@SuppressWarnings("serial")
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // HTTP 클라이언트에게 보낼 콘텐트의 MIME 타입을 설정한다.
    resp.setContentType("text/plain;charset=UTF-8");
    // HTTP 클라이언트에게 콘텐트를 출력한 도구를 준비한다.
    PrintWriter out = resp.getWriter();
    // HTTP 클라이언트에게 콘텐트를 출력한다.
    out.println("게시글 상세보기");

  }

}
