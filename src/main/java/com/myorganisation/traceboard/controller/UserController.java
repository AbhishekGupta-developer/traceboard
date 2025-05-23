package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.UserRequestDTO;
import com.myorganisation.traceboard.dto.UserResponseDTO;
import com.myorganisation.traceboard.model.enums.UserRole;
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
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam Long phone,
            @RequestParam UserRole role,
            @RequestParam(required = false) MultipartFile photo
            ) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName(name);
        userRequestDTO.setEmail(email);
        userRequestDTO.setPhone(phone);
        userRequestDTO.setRole(role);
        return new ResponseEntity<>(userService.registerUser(userRequestDTO, photo), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/photo/{userId}")
    public ResponseEntity<String> getUserPhoto(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserPhoto(userId), HttpStatusCode.valueOf(200));
    }
}
