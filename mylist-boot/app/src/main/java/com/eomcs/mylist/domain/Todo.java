package com.eomcs.mylist.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Todo {
  int no;
  String title;
  boolean done;

}


