package com.eomcs.app1;

import java.util.ArrayList;

public class Command {
  String orderName;
  ArrayList params = new ArrayList(); // java에서 제공하는 ArrayList

  public Command(String orderName, String[] values) {
    this.orderName = orderName;
    for(String value : values) {
      params.add(value);
    }
  }

  public String getName() {
    return /*this.*/orderName;
  }

  public String getString(int paramIndex) {
    return (String) /*this.*/params.get(paramIndex);
  }

  public int getInt(int paramIndex) {
    return Integer.parseInt((String) this.params.get(paramIndex));
  }

  public int getParamSize() {
    return this.params.size(); 
  }
}
