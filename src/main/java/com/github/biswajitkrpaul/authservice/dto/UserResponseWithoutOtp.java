package com.github.biswajitkrpaul.authservice.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

public record UserResponseWithoutOtp(UUID id, long mobile, String firstName, String lastName) {
}