package com.dcc.controller;


import com.dcc.dto.AuthenticationResoponce;
import com.dcc.enities.Profile;
import com.dcc.service.AuthService;
import com.dcc.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(ProfileService profileService, AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResoponce> register(
            @RequestBody Profile profile
    ) {
        return ResponseEntity.status(200).body(authService.register(profile));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResoponce> login(
            @RequestBody Profile profile
    ){
        return  ResponseEntity.ok(authService.authenticate(profile));
    }
}
