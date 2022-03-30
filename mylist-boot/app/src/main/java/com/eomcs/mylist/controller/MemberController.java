package com.eomcs.mylist.controller;

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
}
