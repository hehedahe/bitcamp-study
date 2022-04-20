package com.eomcs.mylist.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // ORG value = ElementType.METHOD => value값이 하나일 때는 생략 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
  String value(); // 페이지 컨트롤러의 path를 저장할 프로퍼티  // 마치 메서드처럼 선언한다. => @Component("/board/list") 가능
}
