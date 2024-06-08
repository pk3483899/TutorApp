package com.tutor.service;

import java.util.Map;

public interface EmailVerficationService {
    public Map<String, String> verifyOtp(String email, String otp);
    public Map<String, String> sendOtpForLogin(String email);
    public Map<String, String> verifyOtpForLogin(String email, String otp);
}
