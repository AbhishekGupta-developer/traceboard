package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.UserRequestDTO;
import com.myorganisation.traceboard.dto.UserResponseDTO;
import com.myorganisation.traceboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerStudent(@RequestBody UserRequestDTO userRequestDTO, @PathVariable MultipartFile photo) {
        return new ResponseEntity<>(userService.registerUser(userRequestDTO, photo), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/photo/{photoId}")
    public ResponseEntity<String> getUserPhoto(@PathVariable Long photoId) {
        return new ResponseEntity<>(userService.getUserPhoto(photoId), HttpStatusCode.valueOf(200));
    }
}
