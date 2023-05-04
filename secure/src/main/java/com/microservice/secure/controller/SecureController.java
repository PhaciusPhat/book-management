package com.microservice.secure.controller;

import com.microservice.secure.entity.DTO.SecureRequestDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SecureRequestDTO secureRequestDTO) {
        authenticate(secureRequestDTO.username(), passwordEncoder.encode(secureRequestDTO.password()));
        return ResponseEntity.ok("");
    }
    //register
    @PostMapping("/register")
    public ResponseEntity<?> register(){
        return ResponseEntity.ok("");
    }
    //validate
    @GetMapping("/validate-token")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok("");
    }
}
