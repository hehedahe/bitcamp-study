package com.eomcs.mylist.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.eomcs.mylist.domain.Member;

@Mapper
public interface MemberDao {

  int insert(Member member);

  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
  // => 파라미터를 두 개 이상 넘길 때, SQL Mapper 에서 "xx" 라는 이름으로 파라미터를 꺼내달라고 요청 

  //  List<Member> findAll();
  //
  //  Member findByNo(int no);
  //
  //  int update(Member member);
  //
  //  int delete(int no);
}











