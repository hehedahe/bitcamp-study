package com.eomcs.oop.ex05.x5;

public class CarTest3 {

  public static void main(String[] args) {
    Sedan sedan = new Sedan();
    ElectricEngine electricOption = new ElectricEngine(sedan);
    electricOption.chargeBattery(100);
    Trailer trailer = new Trailer(electricOption); // 기능을 뺐다 꼈다 바꿀 수 있음!
    //    Trailer trailer = new Trailer(sedan);
    trailer.start();
    trailer.run();
    trailer.stop();

    System.out.println("-------------------");

    Truck truck = new Truck();
    Trailer trailer2 = new Trailer(truck);
    trailer2.start();
    trailer2.run();
    trailer2.stop();
  }
}
