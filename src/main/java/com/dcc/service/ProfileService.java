package com.dcc.service;

import com.dcc.enities.Profile;
import com.dcc.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfileService implements IProfileService {
    private final ProfileRepo profileRepo;

    @Autowired
    public ProfileService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    @Override
    public void updateProfile(Profile profile, long id) {
        //check for profile
        Profile existingProfile = profileRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setEmail(profile.getFirstName());
        existingProfile.setEmail(profile.getLastName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setEmail(profile.getPhoneNumber());
    }

    @Override
    public List<Profile> readProfile() {
        return profileRepo.findAll();
    }

    @Override
    public Profile readProfileById(long id) {
        return profileRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
    }

    @Override
    public Profile readProfileByEmail(String email) {
        return profileRepo.findByEmail(email);
    }

    @Override
    public void deleteProfile(long id) {
        profileRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepo.findByEmail(username);
    }
}
