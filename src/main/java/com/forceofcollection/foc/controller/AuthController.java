package com.forceofcollection.foc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.model.auth.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.auth.LoginRequest;
import com.forceofcollection.foc.model.auth.SignUpRequest;
import com.forceofcollection.foc.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
	private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("/user")
    public ResponseEntity<JwtAuthenticationResponse> refreshUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.refreshUser(userDetails.getUsername()));
    }
    
}
