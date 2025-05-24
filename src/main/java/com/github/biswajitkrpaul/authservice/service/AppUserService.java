package com.github.biswajitkrpaul.authservice.service;

import com.github.biswajitkrpaul.authservice.database.entity.AppUser;
import com.github.biswajitkrpaul.authservice.database.repositories.AppUserRepository;
import com.github.biswajitkrpaul.authservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@Service
public class AppUserService {
    private final AppUserRepository repository;
    private final AppUtils appUtils = new AppUtils();

    @Value("${otp.expire.mins}")
    private int otpExpiresInMinutes;

    public AppUserService(final AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    public AppUser requestOtp(long mobile) {
        var user = repository.findByMobile(mobile).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid login details"));
        var otp = appUtils.generateOtp();
        user.setOtp(otp);
        user.setOtpExpiryDate(Instant.now().plus(Duration.ofMinutes(otpExpiresInMinutes)));
        repository.save(user);
        return user;
    }


    public AppUser createUser(long mobile, String firstName, String lastName) {
        final var hasUserWithSameNumber = repository.findByMobile(mobile);
        if (hasUserWithSameNumber.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid Details");
        }
        final var user = new AppUser();
        user.setMobile(mobile);
        user.setFirstName(firstName.trim());
        user.setLastName(lastName.trim());
        return repository.save(user);
    }

    public AppUser getUser(long mobile) {
        var user = repository.findByMobile(mobile);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid mobile");
        }
        return user.get();
    }
}
