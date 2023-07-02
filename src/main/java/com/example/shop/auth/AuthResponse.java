package com.example.shop.auth;

import lombok.Builder;

/**
 * Date-7/2/2023
 * Time-7:30 AM
 */
@Builder
public class AuthResponse {
    private String token;

    public AuthResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }
}
