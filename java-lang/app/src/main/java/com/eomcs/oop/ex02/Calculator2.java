package com.eomcs.oop.ex02;


public class Calculator2 {

  int result = 0;

  static void plus(Calculator that, int a) {
    that.result += a;
  }

  static void minus(Calculator that, int a) {
    that.result -= a;
  }

  static void multiple(Calculator that, int a) {
    that.result *= a;
  }

  static void divide(Calculator that, int a) {
    that.result /= a;
  }
}