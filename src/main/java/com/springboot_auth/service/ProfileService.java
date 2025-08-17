package com.springboot_auth.service;

import com.springboot_auth.dto.request.ProfileRequest;
import com.springboot_auth.dto.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile(ProfileRequest profileRequest);

    ProfileResponse getProfile(String email);

    void sendResetOtp(String email);

    void resetPassword(String email, String otp, String newPassword);

    void sendOtp(String email);

    void verifyOtp(String email, String otp);
}
