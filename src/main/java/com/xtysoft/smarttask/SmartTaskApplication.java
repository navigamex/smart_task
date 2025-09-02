package com.xtysoft.smarttask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.xtysoft.smarttask.mapper")
@ComponentScan({"com.xtysoft.smarttask.**" })
@EnableScheduling
public class SmartTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartTaskApplication.class, args);
    }

}
