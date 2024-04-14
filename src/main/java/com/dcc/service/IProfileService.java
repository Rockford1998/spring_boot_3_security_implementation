package com.dcc.service;

import com.dcc.enities.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IProfileService extends UserDetailsService {
    Profile createProfile(Profile profile);
    void updateProfile(Profile profile,long id);
    List<Profile> readProfile();
    Profile readProfileById(long id);
    Profile readProfileByEmail(String email);
    void deleteProfile(long id);

}
