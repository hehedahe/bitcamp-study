package com.eomcs.io.ex11.a;

public class Member {
  String name;
  int age;
  boolean gender; // true(여자), false(남자)

  @Override
  public String toString() {
    return "Member [name=" + /*this.*/name + ", age=" + age + ", gender=" + gender + "]";
  } // Java에서는 this. 생략해도 되지만 JS에서는 안된다.
}
