package com.shikkhaerp.modules.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String userId;
    private String email;
    private String name;
    private String role;
    private long expiresIn;
    
    public AuthResponse(String token, String userId, String email, String name, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
        this.tokenType = "Bearer";
        this.expiresIn = 86400000L;
    }
}