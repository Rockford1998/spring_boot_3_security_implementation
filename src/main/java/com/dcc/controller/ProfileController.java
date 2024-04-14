package com.dcc.controller;

import com.dcc.enities.Profile;
import com.dcc.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
        try {
            Profile profile1 = profileService.createProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(profile1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> readProfiles() {
        try {
            List<Profile> profiles = profileService.readProfile();
            return ResponseEntity.status(HttpStatus.OK).body(profiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readProfileByID(@PathVariable Long id) {
        try {
            Profile profiles = profileService.readProfileById(id);
            return ResponseEntity.status(HttpStatus.OK).body(profiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfileByID(@RequestBody Profile profile,@PathVariable Long id) {
        try {
            profileService.updateProfile(profile, id);
            return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfileByID(@PathVariable Long id) {
        try {
             profileService.deleteProfile(id);
            return ResponseEntity.status(HttpStatus.OK).body("Profile deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
