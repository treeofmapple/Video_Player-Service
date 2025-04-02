package com.tom.service.userstorage.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

	ANONYMOUS(Set.of(
			Permission.ANONYMOUS_READ
		)
	),
	
	USER(Set.of(
			Permission.USER_READ,
			Permission.USER_CREATE, 
			Permission.USER_UPDATE, 
			Permission.USER_DELETE
		)
	),
	
	MODERATOR(Set.of(
			Permission.MODERATOR_READ,
			Permission.MODERATOR_CREATE,
			Permission.MODERATOR_UPDATE,
			Permission.MODERATOR_DELETE,
			Permission.USER_READ,
			Permission.USER_CREATE,
			Permission.USER_UPDATE,
			Permission.USER_DELETE
		)
	),
	
	ADMIN(Set.of(
			Permission.ADMIN_READ, 
			Permission.ADMIN_CREATE, 
			Permission.ADMIN_UPDATE, 
			Permission.ADMIN_DELETE,
			Permission.MODERATOR_READ,
			Permission.MODERATOR_CREATE,
			Permission.MODERATOR_UPDATE,
			Permission.MODERATOR_DELETE,
			Permission.USER_READ,
			Permission.USER_CREATE,
			Permission.USER_UPDATE,
			Permission.USER_DELETE,
			Permission.ANONYMOUS_READ
		)
	)
	;
	
	@Getter
	private final Set<Permission> permissions;
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}
	
}
