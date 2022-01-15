package com.eomcs.oop.ex05.x6;

public class Suv extends Car {

  public boolean enabled4wd;

  @Override
  public void run() {
    if (enabled4wd) {
      System.out.println("강력한 파워로 달린다!");
    } else {
      System.out.println("그냥 달린다!");
    }
  }

  public void active4Wd(boolean enable) {
    this.enabled4wd = enable;
  }
}
