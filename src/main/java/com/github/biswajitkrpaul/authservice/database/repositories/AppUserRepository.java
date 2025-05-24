package com.github.biswajitkrpaul.authservice.database.repositories;

import com.github.biswajitkrpaul.authservice.database.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByMobile(long mobile);
}
