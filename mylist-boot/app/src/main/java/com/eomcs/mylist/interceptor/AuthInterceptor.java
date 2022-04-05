package com.eomcs.mylist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 사용자 인증 여부를 검사하는 인터셉터
public class AuthInterceptor implements HandlerInterceptor {
  // HandlerIntercepter 인터페이스를 임플리먼트하더라도 에러가 안뜨는 이유?
  // => 메서드들이 모두 default 메서드로 구현되어 있기 때문!
  // => 인터페이스가 갖고 있는 메서드를 직접 구현하지 않고 오버라이딩만 하면 된다.

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("preHandle() 호출됨!");
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle() 호출됨!");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}
