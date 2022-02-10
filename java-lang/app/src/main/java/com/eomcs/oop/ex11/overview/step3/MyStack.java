package com.eomcs.oop.ex11.overview.step3;

import java.util.EmptyStackException;

public class MyStack extends MyList {

  public void push(Object obj) {
    this.add(obj);
  }

  public Object pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    return remove(size - 1); // 마지막부터 꺼낸다.
  }

  @Override
  public Iterator iterator() {
    return new StackIterator(this); // 상속 받은 메서드 중에서 서브 클래스의 역할에 맞게 메서드를 재정의
  }
}
