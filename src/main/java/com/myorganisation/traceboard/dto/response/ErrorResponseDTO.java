package com.myorganisation.traceboard.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDTO {
    private LocalDateTime localDateTime;
    private String message;
    private String details;

    public ErrorResponseDTO(
            LocalDateTime localDateTime,
            String message,
            String details
    ) {
        this.localDateTime = localDateTime;
        this.message = message;
        this.details = details;
    }
}
