package com.eomcs.mylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableTransactionManagement // 애노테이션으로 트랜잭션을 제어할 수 있게 한다.
@RestController
@SpringBootApplication
public class App {

  //  @Bean
  //  PlatformTransactionManager tansactionManager(DataSource ds) {
  //    return new DataSourceTransactionManager(ds);
  //  }
  // => SpringBoot가 개발자 대신 세팅해주기 때문에 트랜잭션 적용 시 설정할 필요없음!

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  //  //@Bean
  //  public CommandLineRunner commandLineRunner(ApplicationContext beanContainer) {
  //    return args -> {
  //
  //      System.out.println("빈 컨테이너가 생성한 객체(빈 컨테이너에 들어 있는 객체):");
  //
  //      String[] beanNames = beanContainer.getBeanDefinitionNames();
  //      for (int i = 0; i < beanNames.length; i++) {
  //        Object bean = beanContainer.getBean(beanNames[i]);
  //        System.out.printf("----> %03d: %s\n", i + 1, bean.getClass().getName());
  //      }
  //    };
  //  }

  @RequestMapping("/hello")
  String hello() {
    return "Hello mylist-boot!";
  }
}