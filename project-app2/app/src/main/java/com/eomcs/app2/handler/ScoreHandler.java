package com.eomcs.app2.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import com.eomcs.app2.vo.Score;
import com.eomcs.util.Prompt;

public class ScoreHandler {

  ArrayList<Score> scores = new ArrayList<>();

  public ScoreHandler() {
    try (BufferedReader in = new BufferedReader(new FileReader("./score.csv"))) {
      String line;
      while ((line = in.readLine()) != null ) {
        String[] values = line.split(",");
        Score score = new Score();
        score.setName(values[0]);
        score.setKor(Integer.parseInt(values[1]));
        score.setEng(Integer.parseInt(values[2]));
        score.setMath(Integer.parseInt(values[3]));

        scores.add(score);
      }

    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
    }
  }

  public void create() {
    Score score = new Score();
    score.setName(Prompt.promptString("이름? "));
    score.setKor(Prompt.promptInt("국어? "));
    score.setEng(Prompt.promptInt("영어? "));
    score.setMath(Prompt.promptInt("수학? "));

    scores.add(score);
  }

  public void list() {
    int count = 0;
    //    for (int i = 0; i < scores.size(); i++) {
    //    Score score = scores.get(i); ... }
    for (Score score : scores) { // Exam0450 iterable 구현체 반복문
      System.out.printf("%d: %s, %d, %.1f\n",
          count++,
          score.getName(),
          score.getSum(),
          score.getAverage());
    }
  }

  public void detail() {
    int no = Prompt.promptInt("번호? ");
    if (no < 0 || no >= scores.size()) {
      System.out.println("번호가 유요하지 않습니다.");
      return; // break가 아닌 이유?
    }

    Score score = scores.get(no);
    System.out.printf("이름: %s\n", score.getName());
    System.out.printf("국어: %d\n", score.getKor());
    System.out.printf("영어: %d\n", score.getEng());
    System.out.printf("수학: %d\n", score.getMath());
    System.out.printf("합계: %d\n", score.getSum());
    System.out.printf("평균: %.1f\n", score.getAverage());
  }

  public void update() {
    int no = Prompt.promptInt("번호? ");
    if (no < 0 || no >= scores.size()) {
      System.out.println("번호가 유요하지 않습니다.");
      return; // break가 아닌 이유?
    }

    Score old = scores.get(no);

    Score score = new Score();
    score.setName(Prompt.promptString("이름(%s)? ", old.getName()));
    score.setKor(Prompt.promptInt("국어(%d)? ", old.getKor()));
    score.setEng(Prompt.promptInt("영어(%d)? ", old.getEng()));
    score.setMath(Prompt.promptInt("수학(%d)? ", old.getMath()));

    scores.set(no, score);

  }

  public void delete() {
    int no = Prompt.promptInt("번호? ");
    if (no < 0 || no >= scores.size()) {
      System.out.println("번호가 유요하지 않습니다.");
      return; // break가 아닌 이유?
    }
    scores.remove(no);
  }

}
