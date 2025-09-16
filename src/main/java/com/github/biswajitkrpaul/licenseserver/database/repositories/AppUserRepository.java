package com.github.biswajitkrpaul.licenseserver.database.repositories;

import com.github.biswajitkrpaul.licenseserver.database.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByMobile(long mobile);
}
