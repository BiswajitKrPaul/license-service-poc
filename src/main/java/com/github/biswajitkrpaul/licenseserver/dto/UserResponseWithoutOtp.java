package com.github.biswajitkrpaul.licenseserver.dto;

import java.util.UUID;

public record UserResponseWithoutOtp(UUID id, long mobile, String firstName, String lastName) {
}