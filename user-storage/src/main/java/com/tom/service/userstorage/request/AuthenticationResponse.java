package com.tom.service.userstorage.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationResponse(
		
        @Schema(
                accessMode = Schema.AccessMode.READ_ONLY, 
                type = "string", 
                description = "User's username", 
                example = "john_doe")
        String username,
        
        @Schema(
                accessMode = Schema.AccessMode.READ_ONLY, 
                type = "string", 
                description = "Access Token (JWT)", 
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,
        
        @Schema(
                accessMode = Schema.AccessMode.READ_ONLY, 
                type = "string", 
                description = "Refresh Token (JWT)", 
                example = "dGhpcyBpcyBhIHNhbXBsZSB0b2tlbi4uLg==")
        String refreshToken
		
		) {

}
