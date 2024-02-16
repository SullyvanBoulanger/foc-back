package com.forceofcollection.foc.service;

import com.forceofcollection.foc.model.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.LoginRequest;
import com.forceofcollection.foc.model.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(LoginRequest request);
}
