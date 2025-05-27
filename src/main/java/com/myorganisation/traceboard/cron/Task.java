package com.myorganisation.traceboard.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    @Scheduled(cron = "*/5 * * * * *")
    public void cronJob() {
        System.out.println("Executes every seconds...");
    }
}
