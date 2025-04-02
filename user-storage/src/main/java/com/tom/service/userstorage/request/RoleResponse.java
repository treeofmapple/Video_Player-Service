package com.tom.service.userstorage.request;

import com.tom.service.userstorage.model.Role;

public record RoleResponse(

		String username,

		String email,

		Role role

) {

}
