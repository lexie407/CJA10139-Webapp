package com.toiukha.groupactivity.springboot;

/**
 * 【轉換註解】
 * 此類為 Spring Boot 版應用程式的啟動入口。
 * 傳統 web.xml 部署仍存在，
 * 但透過此類可在過渡期啟用 Spring Boot 自動化設定。
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(GroupActivityApplication.class, args);
    }
}
