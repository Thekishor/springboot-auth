package com.springboot_auth.service;

import com.springboot_auth.dto.request.ProfileRequest;
import com.springboot_auth.dto.response.ProfileResponse;
import com.springboot_auth.entity.User;
import com.springboot_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        User user = convertToUserEntity(profileRequest);
        if (!userRepository.existsByEmail(profileRequest.getEmail())) {
            User saveUser = userRepository.save(user);
            return convertToUserResponse(saveUser);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    @Override
    public ProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return convertToUserResponse(user);
    }

    @Override
    public void sendResetOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        //Generate 6 digit OTP using secure approach
        SecureRandom random = new SecureRandom();
        String otp = String.format("%06d", random.nextInt(1000000));
        long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);
        user.setResetOtp(otp);
        user.setResetOtpExpiredAt(expiryTime);

        userRepository.save(user);

        try {
            emailService.sendResetOtpEmail(user.getEmail(), otp);
        } catch (Exception exception) {
            throw new RuntimeException("Unable to send email");
        }
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        if (user.getResetOtp() == null || !user.getResetOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }
        if (user.getResetOtpExpiredAt() < System.currentTimeMillis()) {
            throw new RuntimeException("OTP Expired");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetOtp(null);
        user.setResetOtpExpiredAt(0L);

        userRepository.save(user);
    }

    @Override
    public void sendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        if (user.getIsAccountVerified() != null && user.getIsAccountVerified()) {
            return;
        }

        //Generate 6 digit OTP using secure approach
        SecureRandom random = new SecureRandom();
        String otp = String.format("%06d", random.nextInt(1000000));
        long expiryTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);

        user.setVerifyOtp(otp);
        user.setVerifyOptExpireAt(expiryTime);
        userRepository.save(user);

        try {
            emailService.sendOtpEmail(user.getEmail(), otp);
        } catch (Exception exception) {
            throw new RuntimeException("Unable to send email");
        }
    }

    @Override
    public void verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        if (user.getVerifyOtp() == null || !user.getVerifyOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        if (user.getVerifyOptExpireAt() < System.currentTimeMillis()) {
            throw new RuntimeException("OTP Expired");
        }
        user.setIsAccountVerified(true);
        user.setVerifyOtp(null);
        user.setVerifyOptExpireAt(0L);

        userRepository.save(user);
    }

    private ProfileResponse convertToUserResponse(User user) {
        return ProfileResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .userId(user.getUserId())
                .isAccountVerified(user.getIsAccountVerified())
                .build();
    }

    private User convertToUserEntity(ProfileRequest request) {
        return User.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAccountVerified(false)
                .resetOtpExpiredAt(0L)
                .verifyOtp(null)
                .verifyOptExpireAt(0L)
                .resetOtp(null)
                .build();
    }
}