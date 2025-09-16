package com.github.biswajitkrpaul.licenseserver.utils;

import java.security.SecureRandom;

public class AppUtils {

    private final SecureRandom secureRandom = new SecureRandom();

    public int generateOtp(){
        return secureRandom.nextInt(9000)+1000;
    }
}
