package com.eomcs.mylist.controller;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
// (fluent = true) => setter 메서드의 리턴값이 ResultMap 본인이 되어, 메서드를 연결해서 사용할 수 있음!
public class ResultMap {
  public static final String SUCCESS = "success";
  public static final String FAIL = "fail";

  private String status;
  private Object data;

  //final 이 붙은 필드는 무조건 처음에 초기화 시켜야 한다.
}
