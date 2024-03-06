package com.b23010225.sastwoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SastWoCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SastWoCApplication.class, args);
    }

}
