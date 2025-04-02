package com.tom.service.userstorage.exception;

import com.tom.service.userstorage.exception.global.GlobalIllegalArgumentException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidRoleException extends GlobalIllegalArgumentException {
	private final String msg;
}
