package com.wk;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wk.springboot.dao")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class);
    }
}
