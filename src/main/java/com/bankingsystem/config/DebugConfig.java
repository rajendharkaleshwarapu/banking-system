//package com.bankingsystem.config;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DebugConfig {
//
//    @Value("${spring.datasource.url:NOT_FOUND}")
//    private String url;
//
//    @PostConstruct
//    public void print() {
//        System.out.println("DB USED: " + url);
//    }
//}
//
