// java.io.Serializable 처럼 사용되는 인터페이스 : java.lang.Cloneable 인터페이스
package com.eomcs.io.ex11.b;

public class Exam0231 {

  static class Score /*extends java.lang.Object*/{ // 수퍼클래스를 지정하지 않으면 java.lang.Object가 자동으로 붙는다.
    String name;

    // 복제 기능을 수행하려면 clone()을 오버라이딩 해야 한다.
    @Override
    public Score clone() throws CloneNotSupportedException {
      return (Score) super.clone();
    }
  }

  public static void main(String[] args) throws Exception {

    Score obj = new Score();

    System.out.println(obj instanceof Cloneable); // false => 이 Score 객체는 복제가 지원되지 않는 클래스입니다!
    // => clone() 할 수 없습니다!

    Score copy = obj.clone(); // 런타임(run-time) 오류!
    // Object에서 상속 받은 clone()을 호출하려면
    // 해당 클래스에 대해 복제 기능을 활성화해야 한다.
    // 방법? java.lang.Cloneable 인터페이스를 구현하라!
  }

}
