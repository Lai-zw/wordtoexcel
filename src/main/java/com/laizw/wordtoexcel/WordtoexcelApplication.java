package com.laizw.wordtoexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/laizw/wordtoexcel/mapper")
public class WordtoexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordtoexcelApplication.class, args);
    }

}
