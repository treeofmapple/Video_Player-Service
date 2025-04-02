package com.tom.service.userstorage.exception.global;

@SuppressWarnings("serial")
public abstract class GlobalIllegalStateException extends IllegalStateException {

	public abstract String getMsg();
}
