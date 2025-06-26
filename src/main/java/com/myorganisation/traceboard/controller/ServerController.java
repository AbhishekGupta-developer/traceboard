package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.ServerStatusResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("api/")
public class ServerController {

    private final Instant serverStartTime;
    private final String applicationName;

    public ServerController(@Value("${spring.application.name}") String applicationName) {
        this.serverStartTime = Instant.now();
        this.applicationName = applicationName;
    }

    @GetMapping
    public ResponseEntity<ServerStatusResponseDTO> checkServerHealth() {
        ServerStatusResponseDTO serverStatusResponseDTO = new ServerStatusResponseDTO(serverStartTime, applicationName);
        return new ResponseEntity<>(serverStatusResponseDTO, HttpStatusCode.valueOf(200));
    }
}