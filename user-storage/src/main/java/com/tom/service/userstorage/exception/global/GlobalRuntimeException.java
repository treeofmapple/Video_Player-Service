package com.tom.service.userstorage.exception.global;

@SuppressWarnings("serial")
public abstract class GlobalRuntimeException extends RuntimeException {
	public abstract String getMsg();
}
