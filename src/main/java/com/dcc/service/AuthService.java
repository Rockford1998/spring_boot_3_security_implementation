package com.dcc.service;

import com.dcc.dto.AuthenticationResoponce;
import com.dcc.enities.Profile;
import com.dcc.repository.ProfileRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final ProfileRepo profileRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(ProfileRepo profileRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.profileRepo = profileRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResoponce register(Profile request) {
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setRole(request.getRole());
        profile.setPassword(passwordEncoder.encode(request.getPassword()));
        profile = profileRepo.save(profile);

        String token = jwtService.generateToken(profile);
        return new AuthenticationResoponce(token);
    }

    public AuthenticationResoponce authenticate(Profile request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Profile profile = profileRepo.findByEmail(request.getUsername());
        String token = jwtService.generateToken(profile);
        return  new AuthenticationResoponce(token);
    }
}
