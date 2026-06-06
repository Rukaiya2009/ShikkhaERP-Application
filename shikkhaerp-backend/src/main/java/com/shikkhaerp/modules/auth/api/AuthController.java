package com.shikkhaerp.modules.auth.api;

import com.shikkhaerp.core.dto.ApiResponse;
import com.shikkhaerp.modules.auth.dto.AuthResponse;
import com.shikkhaerp.modules.auth.dto.LoginRequest;
import com.shikkhaerp.modules.auth.dto.RegisterRequest;
import com.shikkhaerp.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(authService.register(request), "Registration successful!");
    }
    
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request), "Login successful!");
    }
    
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        String token = refreshToken.substring(7);
        return ApiResponse.success(authService.refreshToken(token), "Token refreshed successfully!");
    }
    
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token.substring(7));
        return ApiResponse.success(null, "Logged out successfully!");
    }
}