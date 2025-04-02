package com.tom.service.userstorage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		
		@Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "You don't need to put a name, but you can put a personal name",
                example = "user123")
		String name,
		
        @NotNull(message = "Username cannot be null")
        @NotBlank(message = "Username cannot be blank")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Provide a username for you can be able to access the system",
                example = "user123")
        String username,
        
        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Provide your email",
                example = "user@email.com")
        String email,
        
        @NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Schema(
                accessMode = Schema.AccessMode.READ_WRITE,
                allowableValues = "String",
                type = "string",
                description = "Basic user password",
                example = "22r@nd@mp@ssw0rd^x@mple")
        String password
		
		) {

}
