package com.eomcs.oop.ex11.overview.step7;

import java.util.Arrays;

public class MyList {

  Object[] arr = new Object[10];
  int size; 

  public void add(Object obj) {
    if (size == arr.length) {
      arr = Arrays.copyOf(arr, arr.length + (arr.length >> 1)); // 오른쪽으로 1비트 이동 = +1
    }
    arr[size++] = obj;
  }

  public Object get(int index) /*throws ArrayIndexOutOfBoundsException*/ {
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException(); 
      // RuntimeException을 상속받았기 때문에 throws ~ 을 넣지 않아도 된다.
      // 예외를 처리하지 말자는 건 아니야! => main()에서 예외를 처리해줘야 한다.
    }
    return arr[index];
  }

  public int size() {
    return size;
  }

  public Object remove(int index) {
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }

    Object old = arr[index];

    for (int i = index; i < (size - 1); i++) {
      arr[i] = arr[i + 1]; // 값을 복사! 옮기는 게 아니다!! => 마지막 항목의 레퍼런스 주소와 땡겨진 항목의 레퍼런스 주소가 같다.
    }

    arr[--size] = null; // 배열의 크기를 줄이고, 마지막 항목에 들어있는 값을 null로 초기화하여 객체의 레퍼런스를 줄인다.
    return old;
  }

  public Iterator iterator() {
    // anonymous class (익명 클래스)
    // - 클래스의 이름이 없다.
    // - 그래서 클래스를 정의한 후 따로 인스턴스를 생성할 수 없다.
    // - 클래스 정의와 인스턴스 생성 문장이 합쳐져 있다.
    //
    return new Iterator() { // () => 수퍼 클래스의 생성자를 생성하는 문법

      int cursor;

      @Override
      public boolean hasNext() {
        return cursor < MyList.this.size(); // 바깥 클래스의 주소를 참조할 때 MyList.this.xxx() 로 사용한다.
      }

      @Override
      public Object next() {
        return MyList.this.get(cursor++); // MyList에서 데이터를 꺼내고 난 다음 cursor를 다음으로 이동한다.
      }
    };
  }
}
