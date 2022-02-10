package com.eomcs.oop.ex11.overview.step4;

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
    return new ListIterator(this);
  }


  // static nested class (스태틱 중첩 클래스)
  // - ListIterator 는 MyList 클래스에서만 직접 사용된다.
  // - 중첩 클래스 문법을 사용하여 명확하게 ListIterator의 사용범위를 제한한다.
  //
  static class ListIterator implements Iterator {

    MyList list;
    int cursor;

    public ListIterator(MyList list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }

    @Override
    public Object next() {
      return list.get(cursor++); // MyList에서 데이터를 꺼내고 난 다음 cursor를 다음으로 이동한다.
    }
  }
}
