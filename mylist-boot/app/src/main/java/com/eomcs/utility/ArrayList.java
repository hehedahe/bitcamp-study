package com.eomcs.utility;

public class ArrayList {

  // 인스턴스 필드(변수)
  // => 인스턴스 필드는 new 명령을 통해 생성한다.
  Object[] list = new Object[5];
  int size = 0; // 배열에 집어넣은 연락처 개수

  // 인스턴스 주소를 앞 쪽에서 받으려면 static 키워드를 붙이면 안된다.
  // 즉, non-static 메서드로 정의해야 한다.
  // 그리고 메서드가 호출될 때 받은 인스턴스를 사용하려면 내장 변수 this를 이용해야 한다.

  // 기능: 
  // - 배열에 항목을 추가한다.
  // - 배열이 꽉 찼으면 배열의 크기를 늘린다.
  // 
  public void add(Object obj) {
    if (this.size == this.list.length) { // 배열이 꽉 찼다면,
      this.list = this.grow(); // 메서드 이름에서 해당 코드에 대한 설명을 짐작할 수 있다. => 배열을 늘려라!
    }
    this.list[this.size++] = obj; // 인스턴스 주소(레퍼런스)가 들어옴
  }

  // 기능:
  // - 배열의 크기를 늘린다.
  // - 기존 배열의 값을 복사해온다.
  Object[] grow() {
    // 기존 배열보다 50% 큰 배열을 새로 만든다.
    //int newCapacity = newLength();
    //String[] arr = new String[newCapacity];
    Object[] arr = new Object[this.newLength()];
    this.copy(arr);
    return arr; // 메모리 위치정보인 배열 주소가 리턴
  }

  // 기능:
  // - 주어진 배열의 길이를 50% 증가시킨 새 배열의 길이를 알려준다.
  int newLength() {
    return this.list.length + (this.list.length >> 1); // 오른쪽으로 1비트 이동 = contact.length / 2
  }

  // 기능:
  // - 배열을 복사한다.
  void copy(Object[] target) { // void는 return값이 없음 // Contact 배열 => Contact 레퍼런스 배열
    // 개발자가 잘못 사용할 것을 대비해서 다음 코드를 추가한다.
    // 즉, target 배열이 source 배열보다 작을 경우 target 배열 크기만큼만 복사한다. 
    int length = this.list.length;
    if (target.length < this.list.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) { // target 배열이 더 작을 경우, target 배열만큼만 돌리겠다
      target[i] = this.list[i]; 
    }
  } // if문 true라면 기존에 갖고 있던 연락처가 일부 삭제되므로 옳은 코드는 아니다!
  // 다만 메서드를 만들 때, 예외가 발생하는 경우가 있는데 그걸 처리하기 위해?

  // 기능: 
  // - 배열에 저장된 목록만 꺼내 새 배열에 담아 리턴한다.
  //
  public Object[] toArray() {
    Object[] arr = new Object[this.size]; // 배열에 저장된 값만 복사할 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) {
      arr[i]= this.list[i]; // 전체 배열에서 값이 들어있는 항목만 복사한다.
    }
    return arr; // 복사한 항목들을 담고있는 새 배열을 리턴한다.
  }

  // 기능:
  // - 배열에서 저장한 항목을 삭제한다.
  public Object remove(int index) {
    if (index < 0 || index >= this.size) { 
      return null;
    }
    Object old = this.list[index];
    // 현재 위치의 다음 항목에서 배열 끝까지 반복하며 앞으로 값을 당겨온다.
    // 뒤에 있는 칭구들을 앞으로 땡겨와야하니 index는 삭제하는 다음 인덱스 기준 => index+1
    for (int i = index + 1; i < this.size ; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.size--;
    return old;
  }

  // 기능: 
  // - 배열의 특정 위치에 값을 변경한다.
  // - 리턴 값:
  //   - 변경하기 전에 저장되어 있던 값
  //
  public Object set(int index, Object obj) {
    if (index < 0 || index >= this.size) { // 값이 저장된 위치가 무효한 인덱스라면 null 리턴
      return null;
    }
    Object old = this.list[index];
    this.list[index]= obj;
    return old;
  }

  public int size() {
    return this.size;
  }

  public Object get(int index) {
    return this.list[index];
  }



}
