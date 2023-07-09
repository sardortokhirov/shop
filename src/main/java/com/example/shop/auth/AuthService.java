package com.example.shop.auth;

import com.example.shop.config.JwtService;
import com.example.shop.model.Supplier;
import com.example.shop.repository.SupplierRepository;
import com.example.shop.token.Token;
import com.example.shop.token.TokenRepository;
import com.example.shop.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        saveToken(savedSup, jwtToken);
        return AuthResponse.builder().token(jwtToken).build();
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
        revokeAllUserTokens(supplier);
        saveToken(supplier, jwtToken);
        return AuthResponse.builder().token(jwtToken).build();
    }

}
