package com.tom.service.userstorage.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidPasswordException extends IllegalStateException {
	private final String msg;
}
