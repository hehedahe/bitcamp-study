// 리터럴: 문자열 리터럴과 escape character
package com.eomcs.study.lang.literal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lang/literal/exam6")
public class Exam6 {

  @GetMapping("/test1")
  public String test1() {
    return "012ABC가각간똘똠똡똥";
  }

  //제어문자: 일반적인 문자가 아님 (escape character)
  @GetMapping("/test2")
  public String test2() {
    // \n : LineFeed
    return "012\nABC\n가각간\n똘똠똡똥";
  }

  @GetMapping("/test3")
  public String test3() {
    // \r : Carriage Return
    // 웹 브라우저에서는 소용X
    return "012\rABC\r가각간\r똘똠똡똥";
  }

  @GetMapping("/test4")
  public String test4() {
    //  \t - Tab, 0x09 
    return "012\tABC\t가각간\t똘똠똡똥";
  }

  @GetMapping("/test5")
  public String test5() { 
    // \b - Backspace, 0x08 
    // 웹 브라우저에서 소용X
    return "012\bABC\b가각간\b똘똠똡똥";
  }

  @GetMapping("/test6")
  public String test6() { 
    // \\ - Backslash, 0x5c
    return "012\\ABC\\가각간\\똘똠똡똥";
  }

  //  \' - Single Quote, 0x27 
  //  \" - Double Quote, 0x22 
}







