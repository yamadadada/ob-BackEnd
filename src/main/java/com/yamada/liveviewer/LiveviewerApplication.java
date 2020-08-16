package com.yamada.liveviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LiveviewerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveviewerApplication.class, args);
    }

}
