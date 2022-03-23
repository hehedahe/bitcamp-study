package com.eomcs.mylist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.mylist.domain.ContactTel;

@Mapper
public interface ContactDao {

  int countAll(); 

  List<Contact> findAll();

  int insert(Contact contact);

  Contact findByNo(int contactNo);

  Contact findByEmail(String email);

  List<Contact> findByName(String name);

  int update(Contact contact);

  int delete(int contactNo);

  List<ContactTel> findTelByContactNo(int contactNo);

  int insertTel(ContactTel tel);

  int insertTels(@Param("contactNo") int contactNo, @Param("tels") List<ContactTel> tels);
  // contactNo 파라미터 값을 sql mapper에서 @Parma에서 지정한 이름으로 쓰겠다!

  int updateTel(ContactTel tel);

  int deleteTel(int telNo);

  int deleteTelByContactNo(int contactNo);
}








