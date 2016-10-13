package io.nortpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class StartApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(StartApplication.class, args);
  }

}