package com.forceofcollection.foc.service.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forceofcollection.foc.entity.User;
import com.forceofcollection.foc.model.JwtAuthenticationResponse;
import com.forceofcollection.foc.model.LoginRequest;
import com.forceofcollection.foc.model.SignUpRequest;
import com.forceofcollection.foc.repository.UserRepository;
import com.forceofcollection.foc.service.AuthenticationService;
import com.forceofcollection.foc.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));
        var user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username/email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
