package com.eomcs.mylist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.eomcs.mylist.domain.Todo;

@Mapper
public interface TodoDao {

  List<Todo> findAll();

  int insert(Todo todo);

  int update(Todo todo);

  int delete(int no);




}
