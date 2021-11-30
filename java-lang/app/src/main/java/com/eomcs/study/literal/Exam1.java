package com.eomcs.study.literal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/literal/exam1")
public class Exam1 {

  @GetMapping("/test1")
  public String test1() {
    return "홍길동";
  }
}
