package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.request.AuthRequestDTO;
import com.myorganisation.traceboard.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );

            //Generate actual token
            return jwtUtil.generateToken(authRequestDTO.getUsername());

        } catch (AuthenticationException e) {
            System.out.println("An exception occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
