package com.forceofcollection.foc.service;

import com.forceofcollection.foc.model.auth.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.auth.LoginRequest;
import com.forceofcollection.foc.model.auth.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(LoginRequest request);

    JwtAuthenticationResponse refreshUser(String username);
}
