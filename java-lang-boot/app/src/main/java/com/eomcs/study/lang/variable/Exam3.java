// 변수: 스프링 부트의 자동 형변환
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.Exam3")
@RequestMapping("/lang/variable/exam3")
public class Exam3 {

  //스프링 부트는 클라이언트가 보낸 값을 파라미터 타입에 맞춰 자동 형변환을 수행한다.
  // => 웹브라우저가 보내는 값은 무조건 "문자열"이다.

  // 1) "100" -> byte로 변환
  // 테스트 URL:
  // => http://localhost:8080/lang/variable/exam3/test1?value=100
  @GetMapping("/test1")
  public String test1(byte value) {
    return "==> " + value;
  }

  // 2) "32767"문자열 -> short로 변환
  @GetMapping("/test2")
  public String test2(short value) {
    return "==> " + value;
  }

  // 3) "456789"문자열 -> int로 변환
  @GetMapping("/test3")
  public String test3(int value) {
    return "==> " + value;
  }

  // 4) "2244445555"문자열 -> long으로 변환
  @GetMapping("/test4")
  public String test4(long value) {
    return "==> " + value;
  }

  // 5) "3.14"문자열 -> float로 변환
  @GetMapping("/test5")
  public String test5(float value) {
    return "==> " + value;
  }

  // 6) "3456.7898"문자열 -> double로 변환
  @GetMapping("/test6")
  public String test6(double value) {
    return "==> " + value;
  }

  // 7) "true"문자열 -> boolean으로 변환
  // - "true", "1", "TRUE" => true
  // - "false", "0", "FALSE" => false
  @GetMapping("/test7")
  public String test7(boolean value) {
    return "==> " + value;
  }

  // 8) "가"문자열 -> char로 변환
  @GetMapping("/test8")
  public String test8(char value) {
    return "==> " + value;
  }

  // 9) "2021-12-06"문자열 -> Date 형식으로 변환
  // 스프링 부트는 날짜 형식의 데이터로 바꿔줄 수 있어. 다른 애들은 안돼
  @GetMapping("/test9")
  public String test9(java.sql.Date value) {
    return "==> " + value;
  }

}
