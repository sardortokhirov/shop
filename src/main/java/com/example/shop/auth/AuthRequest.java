package com.example.shop.auth;

import lombok.Builder;

/**
 * Date-7/2/2023
 * Time-7:30 AM
 */
@Builder
public class AuthRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthRequest() {
    }
}
