package com.eomcs.oop.ex05.x6;

public abstract class Option extends Car {

  Car car; // 포함할 객체

  // 생성자에서 의존하는(포함하는) Car 객체를 받아야 한다!
  public Option(Car car) {
    this.car = car;
  }
}
