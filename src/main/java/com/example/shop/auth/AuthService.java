package com.example.shop.auth;

import com.example.shop.config.JwtService;
import com.example.shop.model.Supplier;
import com.example.shop.repository.SupplierRepository;
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

    public AuthResponse register(RegisterRequest request) {

        Supplier supplier = Supplier.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .cardNumber("111111111111")
                .build();
        supplierRepository.save(supplier);
        var jwtToke = jwtService.generateToken(supplier);
        return AuthResponse.builder().token(jwtToke).build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = supplierRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

}
