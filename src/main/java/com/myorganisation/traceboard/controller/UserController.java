package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.request.UserRequestDTO;
import com.myorganisation.traceboard.dto.response.UserResponseDTO;
import com.myorganisation.traceboard.model.enums.UserRole;
import com.myorganisation.traceboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
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

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userRequestDTO), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<String> removeUser(@RequestParam Long id) {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserResponseDTO>> getUserPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    ) {
        return new ResponseEntity<>(userService.getUserPage(page, size, sortBy, orderBy), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/photo/{userId}")
    public ResponseEntity<String> getUserPhoto(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserPhoto(userId), HttpStatusCode.valueOf(200));
    }
}
