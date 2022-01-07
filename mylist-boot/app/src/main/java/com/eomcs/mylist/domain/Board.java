package com.eomcs.mylist.domain;

import java.sql.Date;

public class Board {

  String title;
  String content;
  int viewCount;
  java.sql.Date createdDate;

  public Board() {
    System.out.println("Board() 호출됨!");
  }

  public static Board valueOf(String csvStr) {
    String[] values = csvStr.split(",");

    Board board = new Board();
    board.setTitle(values[0]); // 배열에 들어있는 각 항목을 객체의 필드에 저장한다.
    board.setContent(values[1]);
    board.setViewCount(Integer.valueOf(values[2]));
    board.setCreatedDate(Date.valueOf(values[3]));

    return board;
  }

  public String toCsvString() {
    return String.format("%s,%s,%d,%s", 
        this.getTitle(), 
        this.getContent(), 
        this.getViewCount(), 
        this.getCreatedDate());
  }

  @Override
  public String toString() {
    return "Board [title=" + title + ", content=" + content + ", viewCount="
        + viewCount + ", createdDate=" + createdDate + "]";
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public java.sql.Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(java.sql.Date createdDate) {
    this.createdDate = createdDate;
  }
}
