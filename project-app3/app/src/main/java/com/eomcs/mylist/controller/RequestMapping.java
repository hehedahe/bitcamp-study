package com.eomcs.mylist.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // ORG value = ElementType.METHOD => value값이 하나일 때는 생략 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
}
