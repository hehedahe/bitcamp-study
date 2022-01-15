package com.eomcs.oop.ex05.x6;

public class CarTest3 {

  public static void main(String[] args) {
    Sedan sedan = new Sedan();
    SnowChain snowChain = new SnowChain(sedan);
    testCar(snowChain);
  }

  static void testCar(Car car) {
    car.start();
    car.run();
    car.stop();
  }

}
