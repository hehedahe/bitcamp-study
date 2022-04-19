package com.eomcs.mylist.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/app/*")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // System.out.println(req.getServletPath()); // => /app
    // System.out.println(req.getPathInfo()); // => /board/list
    String controllerPath = request.getPathInfo();

    try {
      response.setContentType("text/html; charset=UTF-8");
      RequestDispatcher 요청배달자 = request.getRequestDispatcher(controllerPath);
      // System.out.println(요청배달자); // url이 유효한지 유효하지 않은지 확인할 수 없음
      요청배달자.include(request, response);

      // page controller 에서 예외가 발생할 경우
      Exception exception = (Exception) request.getAttribute("exception");
      if (exception != null) {
        throw exception; // 예외를 던져서 아래 catch에서 잡는다.
      }

      String viewUrl = (String) request.getAttribute("viewUrl");
      request.getRequestDispatcher(viewUrl).include(request, response);

    } catch (Exception e) { // 클라이언트가 요청한 url이 유효하지 않을 때 (예외가 발생하면)
      if (request.getAttribute("exception") == null) {
        request.setAttribute("exception", e);
      }
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }
}
