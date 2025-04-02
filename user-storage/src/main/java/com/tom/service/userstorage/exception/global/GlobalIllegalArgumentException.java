package com.tom.service.userstorage.exception.global;

@SuppressWarnings("serial")
public abstract class GlobalIllegalArgumentException extends IllegalArgumentException {
	public abstract String getMsg();
}
