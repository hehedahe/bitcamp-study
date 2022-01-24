package com.eomcs.mylist.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.eomcs.mylist.domain.Board;
import com.eomcs.util.ArrayList;

public class CsvBoardDao {
  ArrayList boardList = new ArrayList();

  public CsvBoardDao() throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("boards.csv"));

    String csvStr;
    while ((csvStr = in.readLine()) != null) {
      boardList.add(Board.valueOf(csvStr)); 
    }

    in.close();
  }


  public void save() throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.csv")));

    Object[] arr = boardList.toArray();
    for (int i = 0; i < boardList.size(); i++) {
      Board board = (Board) boardList.get(i);
      out.println(board.toCsvString());
    }

    out.flush();
    out.close();

  }

  public int countAll() {
    return boardList.size();
  }



}
