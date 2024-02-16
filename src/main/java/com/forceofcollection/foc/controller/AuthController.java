package com.forceofcollection.foc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.model.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.LoginRequest;
import com.forceofcollection.foc.model.SignUpRequest;
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
}
