package com.myorganisation.traceboard.dto.response;

import lombok.Getter;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Getter
public class ServerStatusResponseDTO {
    private final String status;
    private final String artifact;
    private final String date;
    private final String time;
    private final String timeZoneId;
    private final String uptime;
    private final String os;

    public ServerStatusResponseDTO(Instant serverStartTime, String applicationName) {
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
        this.uptime = calculateUptime(serverStartTime);
        this.os = String.format("%s (%s)", System.getProperty("os.name"), System.getProperty("os.version"));
    }

    private String calculateUptime(Instant serverStartTime) {
        Duration duration = Duration.between(serverStartTime, Instant.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d hours %02d minutes %02d seconds", hours, minutes, seconds);
    }
}
