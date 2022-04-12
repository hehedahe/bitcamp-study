package com.eomcs.mylist.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.mylist.dao.MemberDao;
import com.eomcs.mylist.domain.Member;
import com.eomcs.mylist.service.MemberService;

public class DefaultMemberService implements MemberService {

  SqlSessionFactory sqlSessionFactory;

  public DefaultMemberService(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int add(Member member) {
    // 주의!
    // - 스레드마다 SqlSession이 구분되어야 한다. 즉, 클라이언트 간의 트랜잭션이 분리되어야 한다.
    // - 따라서 스레드가 서비스 메서드를 호출하는 시점에서 SqlSession을 얻어 DAO를 준비해야 한다.
    // 
    SqlSession session = sqlSessionFactory.openSession();
    MemberDao memberDao = session.getMapper(MemberDao.class);
    return memberDao.insert(member);
  }

  @Override
  public Member get(String email, String password) {
    SqlSession session = sqlSessionFactory.openSession();
    MemberDao memberDao = session.getMapper(MemberDao.class);
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public Member get(String email) {
    SqlSession session = sqlSessionFactory.openSession();
    MemberDao memberDao = session.getMapper(MemberDao.class);
    return memberDao.findByEmail(email);
  }

}