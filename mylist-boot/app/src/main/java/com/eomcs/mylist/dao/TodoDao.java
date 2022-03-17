package com.eomcs.mylist.dao;

import java.util.List;
import com.eomcs.mylist.domain.Todo;

public interface TodoDao {

  List<Todo> findAll();

}
