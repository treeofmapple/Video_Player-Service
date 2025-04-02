package com.tom.service.userstorage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
		
		@NotNull(message = "Current password cannot be null.")
		@NotBlank(message = "Current password cannot be blank.")
		@Schema(
				accessMode = Schema.AccessMode.READ_WRITE,
				allowableValues = "String",
				type = "string",
				description = "Current user password",
				example = "22r@nd@mp@ssw0rd^x@mple"
				)
		String currentPassword,
		
		@NotNull(message = "New password cannot be null.")
		@NotBlank(message = "New password cannot be blank.")
		@Min(value = 8, message = "New password must be at least 8 characters long.")
		@Schema(
				accessMode = Schema.AccessMode.READ_WRITE,
				allowableValues = "String",
				type = "string",
				description = "User new password",
				example = "22r@nd@mp@ssw0rd^x@mple"
				)
		String newPassword,
		
		@NotNull(message = "Confirmation password cannot be null.")
		@NotBlank(message = "Confirmation password cannot be blank.")
		@Min(value = 8, message = "Confirmation password must be at least 8 characters long.")
		@Schema(
				accessMode = Schema.AccessMode.READ_WRITE,
				allowableValues = "String",
				type = "string",
				description = "Confirm user new password",
				example = "22r@nd@mp@ssw0rd^x@mple"
				)
		String confirmationPassword
		
		) {

}
