package com.eomcs.mylist.interceptor;

import static com.eomcs.mylist.controller.ResultMap.FAIL;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.mylist.controller.ResultMap;
import com.eomcs.mylist.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;

// 사용자 인증 여부를 검사하는 인터셉터
public class AuthInterceptor implements HandlerInterceptor {
  // HandlerIntercepter 인터페이스를 임플리먼트하더라도 에러가 안뜨는 이유?
  // => 메서드들이 모두 default 메서드로 구현되어 있기 때문!
  // => 인터페이스가 갖고 있는 메서드를 직접 구현하지 않고 오버라이딩만 하면 된다.

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("preHandle() 호출됨!");

    // 로그인 여부 검사
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser != null) {
      // 로그인을 하지 않았으면 오류 메세지를 JSON형식으로 직접 응답한다.
      ObjectMapper jsonConverter = new ObjectMapper();
      // 그 전에는 컨트롤러에서 jackson의 ObjectMapper가 자동으로 json형식으로 변환해주었는데,
      // 인터셉터는 직접 ObjectMapper를 생성해서 처리해줘야해!
      String json =  jsonConverter.writeValueAsString(new ResultMap()
          .setStatus(FAIL)
          .setData("로그인하지 않았습니다!"));
      response.setContentType("application/json;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.write(json);

      return false; // 페이지 컨트롤러를 실행하지 말고 즉시 응답하라!
    }
    return true; // 로그인된 상태라면, 계속 진행하라! => 요청한 페이지 컨트롤러의 메서드를 호출하라!
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle() 호출됨!");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}
