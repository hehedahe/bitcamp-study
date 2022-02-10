package com.eomcs.oop.ex11.overview.step6;

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
    // local class (로컬 클래스) -> 이미 중첩 클래스 의미 내포
    // - 메서드나 특정 블록 안에서만 사용될 클래스라면 사용 범위를 더 제한할 수 있다.
    //   그 메서드나 블록에서 클래스를 정의함으로써
    //   명시적으로 사용 범위를 더 제한할 수 있다.
    // - 단지 사용 범위를 더 제한한 것에 불과하다.
    // - 로컬 클래스에도 바깥 클래스의 인스턴스 주소를 저장할 필드와 생성자가 자동으로 추가된다.
    //
    class ListIterator implements Iterator { // 로컬 변수에 public을 붙일 수 없듯이 로컬 클래스에도 접근범위 키워드를 붙일 수 없다.

      int cursor;

      @Override
      public boolean hasNext() {
        return cursor < MyList.this.size(); // 바깥 클래스의 주소를 참조할 때 MyList.this.xxx() 로 사용한다.
      }

      @Override
      public Object next() {
        return MyList.this.get(cursor++); // MyList에서 데이터를 꺼내고 난 다음 cursor를 다음으로 이동한다.
      }
    }

    return new ListIterator();
  }



}
