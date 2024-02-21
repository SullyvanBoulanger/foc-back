package com.forceofcollection.foc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.model.auth.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.auth.LoginRequest;
import com.forceofcollection.foc.model.auth.SignUpRequest;
import com.forceofcollection.foc.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refreshUser() {
        return new ResponseEntity<>("Response body", HttpStatus.OK);
    }
    
}
