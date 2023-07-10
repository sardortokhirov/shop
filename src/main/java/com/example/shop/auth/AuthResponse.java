package com.example.shop.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date-7/2/2023
 * Time-7:30 AM
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    @JsonProperty("access_token")
    private String accsesToken;

    @JsonProperty("refresh_token")
    private String refreshToken;


}
