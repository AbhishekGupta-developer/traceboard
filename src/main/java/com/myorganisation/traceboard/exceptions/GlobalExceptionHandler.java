package com.myorganisation.traceboard.exceptions;

import com.myorganisation.traceboard.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<ErrorResponseDTO> handleUserDoesNotExist(UserDoesNotExist e) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = e.getMessage();
        String details = "User doesn't exist!";

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(localDateTime, message, details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatusCode.valueOf(404));
    }
}
