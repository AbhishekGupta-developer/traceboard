package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.request.UserRequestDTO;
import com.myorganisation.traceboard.dto.response.UserResponseDTO;
import com.myorganisation.traceboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/user")
public class UserV2Controller {

    @Autowired
    private UserService userService;

    @GetMapping
    public String test() {
        return "Should not allow without authentication";
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.registerUser(userRequestDTO, null), HttpStatus.CREATED);
    }
}
