package com.betomorrow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RandomNumberPushApp {

    public static void main(String[] args)
    {
        SpringApplication.run(RandomNumberPushApp.class, args);
    }

}