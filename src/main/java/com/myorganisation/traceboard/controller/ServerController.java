package com.myorganisation.traceboard.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/")
public class ServerController {

    private final Instant serverStartTime = Instant.now();

    @Value("${spring.application.name}")
    String applicationName;

    @GetMapping
    public ResponseEntity<ServerStatus> checkServerHealth() {
        return new ResponseEntity<>(new ServerStatus(), HttpStatusCode.valueOf(200));
    }

    @Getter
    public class ServerStatus {
        private final String status;
        private final String artifact;
        private final String date;
        private final String time;
        private final String timeZoneId;
        private final String uptime;
        private final String os;

        public ServerStatus() {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            ZoneId zoneId = ZoneId.systemDefault();
            ZoneOffset zoneOffset = OffsetDateTime.now(zoneId).getOffset();

            this.status = "Server is live!";
            this.artifact = applicationName;
            this.date = currentDateTime.format(dateFormatter);
            this.time = currentDateTime.format(timeFormatter);
            this.timeZoneId = String.format("%s (UTC%s)", zoneId.getId(), zoneOffset);
            this.uptime = calculateUptime();
            this.os = System.getProperty("os.name") + " (" + System.getProperty("os.version") + ")";
        }

        private String calculateUptime() {
            Duration duration = Duration.between(serverStartTime, Instant.now());
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            return String.format("%02d hours %02d minutes %02d seconds", hours, minutes, seconds);
        }
    }
}
