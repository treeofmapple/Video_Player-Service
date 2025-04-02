package com.tom.service.userstorage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserRoleRequest(
		
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
		
        @NotNull(message = "Email cannot be null")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Provide a role to change",
                example = "USER, MODERATOR, ADMIN")
		String Role
		
		) {

}
