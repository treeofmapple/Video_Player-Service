package com.tom.service.userstorage.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.tom.service.userstorage.exception.InvalidRoleException;
import com.tom.service.userstorage.exception.NotFoundException;
import com.tom.service.userstorage.mapper.UserMapper;
import com.tom.service.userstorage.model.Role;
import com.tom.service.userstorage.repository.UserRepository;
import com.tom.service.userstorage.request.DeleteResponse;
import com.tom.service.userstorage.request.RoleResponse;
import com.tom.service.userstorage.request.UserRequest;
import com.tom.service.userstorage.request.UserRoleRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

	private final UserRepository repository;
	private final UserMapper mapper;

	public RoleResponse changeUserRole(UserRoleRequest request) {
		var user = repository.findByUsername(request.username()).or(() -> repository.findByEmail(request.email()))
				.orElseThrow(() -> {
					log.warn("The requested user: {}, was not found", request);
					return new NotFoundException(String.format("User was not found %s", request));
				});
		
		Role roles = Arrays.stream(Role.values())
		        .filter(r -> r.name().equalsIgnoreCase(request.Role().toUpperCase())) 
		        .findFirst()
		        .orElseThrow(() -> new InvalidRoleException("Invalid role: " + request.Role().toUpperCase()));

		user.setRole(roles);
		return mapper.buildRoleResponse(user);
	}

	public DeleteResponse removeUser(UserRequest request) {
		var user = repository.findByUsername(request.username()).or(() -> repository.findByEmail(request.email()))
				.orElseThrow(() -> {
					log.warn("The requested user: {}, was not found", request);
					return new NotFoundException(String.format("User was not found %s", request));
				});
		repository.delete(user);
		var answer = mapper.buildDeleteResponse(user);
		return answer;
	}

}
