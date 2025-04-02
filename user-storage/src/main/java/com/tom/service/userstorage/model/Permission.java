package com.tom.service.userstorage.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    MODERATOR_READ("moderator:read"),
    MODERATOR_CREATE("moderator:create"),
    MODERATOR_UPDATE("moderator:update"),
    MODERATOR_DELETE("moderator:delete"),
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    ANONYMOUS_READ("anonymous:read")
    ;

    @Getter
    private final String permission;
	
}
