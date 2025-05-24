package com.github.biswajitkrpaul.authservice.controllers;


import com.github.biswajitkrpaul.authservice.dto.UserResponseWithoutOtp;
import com.github.biswajitkrpaul.authservice.service.AppUserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final AppUserService appUserService;

    private UserController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    private ResponseEntity<UserResponseWithoutOtp> getUserDetails(@Param("mobile") long mobile) {
        var user = appUserService.getUser(mobile);
        return ResponseEntity.ok().body(new UserResponseWithoutOtp(user.getId(), user.getMobile(), user.getFirstName(),
                user.getLastName()));
    }
}
