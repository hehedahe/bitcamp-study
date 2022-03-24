package com.eomcs.web.html;

import java.io.File;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FormController {

  @RequestMapping("/html/form/exam01")
  public Object exam01() {
    return "Hello!";
  }

  @RequestMapping("/html/form/exam02")
  public Object exam02(String name, int age) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", name);
    map.put("age", age);

    return map;
  }

  @RequestMapping("/html/form/exam11")
  public Object exam11(String name, int age, MultipartFile photo) { // => 클라이언트가 보낸 파일 photo로 받기
    System.out.println(name);
    System.out.println(age);
    System.out.println(photo.getOriginalFilename());

    try {
      File photoFile = new File("c:/upload/" + photo.getOriginalFilename()); // "c:\\upload\\" => File 객체로 경로 지정
      photo.transferTo(photoFile); // => photo로 받은 파일을 위에 지정한 경로에 저장
    } catch (Exception e) {
      e.printStackTrace();
      return "error!";
    }
    return "ok!";
  }

  @RequestMapping("/html/form/exam12")
  public Object exam12(String name, int age, MultipartFile[] photo) { // photo는 exam-12.html 변수명과 똑같아야해!
    System.out.println(name);
    System.out.println(age);

    for (MultipartFile part : photo) {
      try {
        File photoFile = new File("c:/upload/" + part.getOriginalFilename()); // "c:\\upload\\" => File 객체로 경로 지정
        part.transferTo(photoFile); // => photo로 받은 파일을 위에 지정한 경로에 저장
      } catch (Exception e) {
        e.printStackTrace();
        return "error!";
      }
    }
    return "ok!";
  }

  @RequestMapping("/html/form/exam21")
  public Object exam21(String name, int age) throws Exception {
    System.out.println(name);
    System.out.println(age);

    //    Thread.sleep(10000); // 동기 요청을 위해 스레드를 10초간 재운다.

    return "ok!";
  }

  @RequestMapping("/html/form/exam31")
  public Object exam31(String name, int age, MultipartFile photo) { // => 클라이언트가 보낸 파일 photo로 받기
    System.out.println(name);
    System.out.println(age);

    if (photo != null && photo.getSize() > 0) {
      System.out.println(photo.getOriginalFilename());

      try {
        File photoFile = new File("c:/upload/" + photo.getOriginalFilename()); // "c:\\upload\\" => File 객체로 경로 지정
        photo.transferTo(photoFile); // => photo로 받은 파일을 위에 지정한 경로에 저장
      } catch (Exception e) {
        e.printStackTrace();
        return "error!";
      }
    }
    return "ok!";
  }
  @RequestMapping("/html/form/exam32")
  public Object exam32(String name, int age, MultipartFile[] photo) { // photo는 exam-12.html 변수명과 똑같아야해!
    System.out.println(name);
    System.out.println(age);


    if(photo !=null ) {
      for (MultipartFile part : photo) {
        try {
          File photoFile = new File("c:/upload/" + part.getOriginalFilename()); // "c:\\upload\\" => File 객체로 경로 지정
          part.transferTo(photoFile); // => photo로 받은 파일을 위에 지정한 경로에 저장
        } catch (Exception e) {
          e.printStackTrace();
          return "error!";
        }
      }
    }
    return "ok!";
  }
  @RequestMapping("/html/form/exam41")
  public Object exam41(String name, int age, MultipartFile[] photo) { // photo는 exam-12.html 변수명과 똑같아야해!
    System.out.println(name);
    System.out.println(age);


    if(photo !=null ) {
      for (MultipartFile part : photo) {
        try {
          File photoFile = new File("c:/upload/" + part.getOriginalFilename()); // "c:\\upload\\" => File 객체로 경로 지정
          part.transferTo(photoFile); // => photo로 받은 파일을 위에 지정한 경로에 저장
        } catch (Exception e) {
          e.printStackTrace();
          return "error!";
        }
      }
    }
    return "ok!";
  }
}