package com.betomorrow.services;

import com.betomorrow.notifications.RandomNumberNotification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class NotificationService {
    public final ApplicationEventPublisher eventPublisher;

    public NotificationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 4000, initialDelay = 2000)
    public void publishNotification() throws InterruptedException {
        int aRandomValue =  new Random().nextInt(100);
        RandomNumberNotification notif = new RandomNumberNotification(aRandomValue, new Date());
        this.eventPublisher.publishEvent(notif);
    }
}
