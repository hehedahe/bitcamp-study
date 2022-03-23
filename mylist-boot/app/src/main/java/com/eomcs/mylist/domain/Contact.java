package com.eomcs.mylist.domain;

import java.util.List;

public class Contact {

  int contactNo;
  String name;
  String email;
  String company;
  List<ContactTel> tels; 
  // => Contact 클래스에 ContactTel 클래스를 참조하는 코드가 없다면 
  //    ContactTel 클래스에 "Contact contact;" 를 넣어 단방향 참조해준다.

  public int getContactNo() {
    return contactNo;
  }
  public void setContactNo(int contactNo) {
    this.contactNo = contactNo;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }
  public List<ContactTel> getTels() {
    return tels;
  }
  public void setTels(List<ContactTel> tels) {
    this.tels = tels;
  }
  @Override
  public String toString() {
    return "Contact [contactNo=" + contactNo + ", name=" + name + ", email=" + email + ", company="
        + company + ", tels=" + tels + "]";
  }





}
