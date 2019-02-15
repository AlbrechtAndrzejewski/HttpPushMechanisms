package com.betomorrow.notifications;

import org.springframework.context.annotation.Bean;

import java.util.Date;


public class RandomNumberNotification {
    private final int randomNumber;
    private final Date dateOfGeneration;

        public RandomNumberNotification(int randomNumber, Date dateOfGeneration) {
            super();
            this.randomNumber =randomNumber;
            this.dateOfGeneration = dateOfGeneration;
        }


    public int getRandomNumber() {
        return randomNumber;
    }

    public Date getDateOfGeneration() {
        return dateOfGeneration;
    }
}
