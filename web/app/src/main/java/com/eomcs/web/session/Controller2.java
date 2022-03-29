package com.eomcs.web.session;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 일반 클래스는 스프링 프레임워크에서 관리하지 않으므로 애노테이션 추가
public class Controller2 {


  @RequestMapping("/session/step1")
  public Object step1(String name, HttpSession session) {
    // 세션에 값 저장
    session.setAttribute("name", name);
    return "step1() 실행!";
  }


  @RequestMapping("/session/step2")
  public Object step2(int age, HttpSession session) {
    session.setAttribute("age", age);
    return "step2() 실행!";
  }


  @RequestMapping("/session/step3")
  public Object step3(boolean working, HttpSession session) {
    session.setAttribute("working", working);
    return "test3() 실행!";
  }

  @RequestMapping("/session/step4")
  public Object step4(HttpSession session) {
    String name = (String) session.getAttribute("name");
    int age = (int) session.getAttribute("age");
    boolean working = (boolean) session.getAttribute("working");

    return String.format("step4() 실행: %s, %d, %b\n", name, age, working);
  }


}
