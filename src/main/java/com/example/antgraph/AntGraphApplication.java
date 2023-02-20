package com.example.antgraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AntGraphApplication {



    @Autowired
    private static ControlDB rep;
    public static void main(String[] args) {

        SpringApplication.run(AntGraphApplication.class, args);

    }

}
