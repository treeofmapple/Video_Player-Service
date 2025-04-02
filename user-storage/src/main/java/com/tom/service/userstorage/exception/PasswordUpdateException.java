package com.tom.service.userstorage.exception;

import com.tom.service.userstorage.exception.global.GlobalRuntimeException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class PasswordUpdateException extends GlobalRuntimeException {

	private final String msg;
	
}
