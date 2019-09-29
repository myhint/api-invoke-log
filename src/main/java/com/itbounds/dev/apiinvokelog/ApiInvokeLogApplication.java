package com.itbounds.dev.apiinvokelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApiInvokeLogApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiInvokeLogApplication.class, args);
  }

}
