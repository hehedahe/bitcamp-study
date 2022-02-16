package com.eomcs.app2.vo;

public class Score {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float average;

  private void compute() {
    //    this.sum = this.kor + this.eng + this.math;
    //    this.average = this.sum / 3f;
    sum = kor + eng + math;
    average = sum / 3f;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getKor() {
    return kor;
  }
  public void setKor(int kor) {
    this.kor = kor;
    /*this.*/compute(); // 어떤 인스턴스를 호출하든 인스턴스 주소 없이 호출할 수 없다! this.가 생략된 것이다. 
  }
  public int getEng() {
    return eng;
  }
  public void setEng(int eng) {
    this.eng = eng;
    compute();
  }
  public int getMath() {
    return math;
  }
  public void setMath(int math) {
    this.math = math;
    compute();
  }
  public int getSum() {
    return sum;
  }
  public float getAverage() {
    return average;
  }

  @Override
  public String toString() {
    return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
        + sum + ", average=" + average + "]";
  }



}
