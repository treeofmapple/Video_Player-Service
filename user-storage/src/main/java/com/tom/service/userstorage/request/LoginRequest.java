package com.tom.service.userstorage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
		
        @NotNull(message = "Username cannot be null")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Provide either your username or email",
                example = "user123")
		String username,
		
        @NotNull(message = "Email cannot be null")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Provide either your email or username",
                example = "user@email.com")
		String email,
		
        @NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Basic user password",
                example = "22r@nd@mp@ssw0rd^x@mple")
		String password
		
		) {

}
