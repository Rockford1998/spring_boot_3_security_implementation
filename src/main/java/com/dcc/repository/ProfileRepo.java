package com.dcc.repository;

import com.dcc.enities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProfileRepo extends JpaRepository<Profile,Long> {
    Profile findByEmail(String email);
}
