package com.eomcs.mylist.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Member;
import com.eomcs.mylist.service.MemberService;

@RestController // 리턴값은 JSON 형태
public class MemberController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/signup")
  public Object signup(Member member) {
    return memberService.add(member);
  }

  @RequestMapping("/member/signin")
  public Object signin(String email, String password, HttpSession session) {
    Member loginUser = memberService.get(email, password);
    if (loginUser == null) {
      return "fail";
    }

    // 로그인이 성공하면, 
    // 다른 요청을 처리할 때 로그인 회원의 정보를 사용할 있도록 세션에 보관한다.
    session.setAttribute("loginUser", loginUser);

    return "success";
  }
}
