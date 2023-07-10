package com.example.shop.auth;

import com.example.shop.config.JwtService;
import com.example.shop.model.Supplier;
import com.example.shop.repository.SupplierRepository;
import com.example.shop.token.Token;
import com.example.shop.token.TokenRepository;
import com.example.shop.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Date-7/2/2023
 * Time-7:29 AM
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SupplierRepository supplierRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    public AuthResponse register(RegisterRequest request) {
        Supplier supplier = Supplier.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .cardNumber("111111111111")
                .build();
        var savedSup = supplierRepository.save(supplier);
        var jwtToken = jwtService.generateToken(supplier);
        var refreshToken = jwtService.generateRefreshToken(supplier);
        saveToken(savedSup, jwtToken);
        return AuthResponse.builder().accsesToken(jwtToken).refreshToken(refreshToken).build();
    }

    private void saveToken(Supplier savedSup, String jwtToken) {
        var token = Token.builder()
                .supplier(savedSup)
                .expired(false)
                .revoked(false)
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Supplier supplier) {
        var validTokens = tokenRepository.findByValidTokensBySupplier(supplier.getId().intValue());
        if (validTokens.isEmpty()) return;
        validTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);


    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var supplier = supplierRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateToken(supplier);
        var refreshToken = jwtService.generateRefreshToken(supplier);
        revokeAllUserTokens(supplier);
        saveToken(supplier, jwtToken);
        return AuthResponse.builder().accsesToken(jwtToken).refreshToken(refreshToken).build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var userDetails = this.supplierRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateToken(userDetails);
                revokeAllUserTokens(userDetails);
                saveToken(userDetails, accessToken);
                var authResponse = AuthResponse.builder().refreshToken(refreshToken).accsesToken(accessToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }


        }
    }
}
