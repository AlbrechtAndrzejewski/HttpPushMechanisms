package com.betomorrow.controller;

import com.betomorrow.notifications.RandomNumberNotification;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by aandrezjewski on 15/02/2018.
 */
@RestController
@EnableAutoConfiguration
public class RandomNumberPushController {
    private int currentRandomNumber;

    @RequestMapping("/getNumber")
    public String getRandomNumber() {

        return "{\"randomNumber\": " + new Integer(currentRandomNumber).toString() + " }";
    }

    @EventListener
    public void onRandomNumberGenerated(RandomNumberNotification notif) {
        currentRandomNumber = notif.getRandomNumber();
    }


}
