// 리터럴: 문자 리터럴
package com.eomcs.study.lang.literal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lang/literal/exam5") // 이 경로를 지우면 바로 메소드 경로 주소를 적기만해도 가능
// 메소드를 찾아가는 경로 이름이기 때문에 굳이 패키지 명을 적지 않아도 됨
//@RequestMapping("/hehedahe")
// but, 경로 이름을 패키지 이름으로 하면 쉽게 찾을 수 있음
public class Exam5 {

  @GetMapping("/test1")
  public String test1() {
    return "문자1: " + 'A' + '가';
  }

  @GetMapping("/test2")
  public String test2() {
    return "문자2: " + '\u0041' + '\uac00'; // 문자에 대한 유니코드 값을 지정해도 됨
  }

  @GetMapping("/test3")
  public String test3() {
    return "문자3: " + 0x41 + "," + 0xac00; // 문자 코드(16진수)를 정수값(10진수)으로 지정
  }

  //→ 대신 문자를 가리키는 정수(문자코드)임을 (char)로 표시해야 한다. 
  @GetMapping("/test4")
  public String test4() {
    return "문자4: " + (char)0x41 + "," + (char)0xac00; 
  }

  @GetMapping("/test5")
  public String test5() {
    return "문자5: " + (char)65 + "," + (char)44032; // 문자의 코드값은 그냥 정수값이다.
  }

  // 키보드에서 입력 불가능한 특수 문자를 가리킬 때, 주로 유니코드 사용
  @GetMapping("/test6")
  public String test6() {
    return "문자6: " + '\u4eba'+ '\u00a9' + '\u03c0' + '\u03a3'; 
  }

}







