package com.github.biswajitkrpaul.authservice.controllers;

import com.github.biswajitkrpaul.authservice.database.entity.AppUser;
import com.github.biswajitkrpaul.authservice.dto.CreateUserRequest;
import com.github.biswajitkrpaul.authservice.dto.SingleMessageDto;
import com.github.biswajitkrpaul.authservice.dto.UserResponseWithoutOtp;
import com.github.biswajitkrpaul.authservice.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping
    public ResponseEntity<UserResponseWithoutOtp> createUser(@RequestBody CreateUserRequest createUserRequest) {
        final AppUser appUser = appUserService.createUser(createUserRequest.mobile(), createUserRequest.firstname(),
                createUserRequest.lastname());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new UserResponseWithoutOtp(appUser.getId(), appUser.getMobile(),
                                     appUser.getFirstName(), appUser.getLastName()));
    }

    @GetMapping
    public ResponseEntity<SingleMessageDto> requestOtp(@RequestParam("mobile") long mobile) {
        var appUser = appUserService.requestOtp(mobile);
        return ResponseEntity.ok().body(new SingleMessageDto("OTP has been sent to " + appUser.getMobile()));
    }
}
