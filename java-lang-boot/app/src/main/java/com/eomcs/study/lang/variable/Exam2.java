// 배열: 같은 종류의 메모리를 여러개 만드는 명령문
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.exam2") //literal/exam2와 구분하기 위한 이름표 
// => 그냥 okok 이렇게 해도 되는데 구분 쉽게 하기 위해 패키지 명에 맞춰 지어줌
@RequestMapping("/lang/variable/exam2")
public class Exam2 {

  // 여러개의 값을 받는 방법: 배열 사용 전
  // http://localhost:8080/lang/variable/exam2/test1?name1=%EC%B1%84&name2=%EC%9C%A0&name3=%EA%B9%80&name4=%EC%86%A1&name5=%ED%99%A9&name6=%EB%B3%80&name7=%EC%9D%B4
  @GetMapping("/test1")
  public String test1(String name1, String name2, String name3, String name4, String name5, String name6, String name7) {
    return "=> " + name1 + ", " + name2 + ", " + name3 + ", " +name4 + ", " +name5 + ", " +name6 + ", " +name7;
  }
  // => 파라미터 갯수만큼 변수 선언해야한다


  // 여러개의 값을 받는 방법: 배열 사용 후
  // 배열 메모리에 값을 넘길 때에는 같은 파라미터 이름을 사용해야 한다.
  // http://localhost:8080/lang/variable/exam2/test2?name=%EC%B1%84&name=%EC%9C%A0&name=%EA%B9%80&name=%EC%86%A1&name=%ED%99%A9&name=%EB%B3%80&name=%EC%9D%B4
  @GetMapping("/test2")
  public String test2(String[] name) {
    return "=> " + name[0] + "," + name[1] + ", " + name[2] + ", " + name[3] + ", " +name[4] + ", " +name[5] + ", " +name[6];
  }



}
