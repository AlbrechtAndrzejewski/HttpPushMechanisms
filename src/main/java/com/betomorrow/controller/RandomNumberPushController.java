package com.betomorrow.controller;

import com.betomorrow.notifications.RandomNumberNotification;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by aandrezjewski on 13/02/2018.
 */
@RestController
@EnableAutoConfiguration
public class RandomNumberPushController {
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    @RequestMapping("/getNumber")
    public SseEmitter getNewNotification() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });
        return emitter;
    }

    @EventListener
    public void onNotification(RandomNumberNotification rngNotification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(rngNotification, MediaType.APPLICATION_JSON_UTF8);
            } catch (Exception e) {
                e.printStackTrace();
                deadEmitters.add(emitter);
            }
        });
        this.emitters.remove(deadEmitters);
    }
}
