package com.tom.service.userstorage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.userstorage.request.DeleteResponse;
import com.tom.service.userstorage.request.RoleResponse;
import com.tom.service.userstorage.request.UserRequest;
import com.tom.service.userstorage.request.UserRoleRequest;
import com.tom.service.userstorage.service.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')") 
@Tag(name = "UrlSystem", description = "Operation of URL Shortening")

public class AdminController {

	private final AdminService service;
	
	@PostMapping("/role/change")
	public ResponseEntity<RoleResponse> changeUserRole(@RequestBody UserRoleRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.changeUserRole(request));
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<DeleteResponse> removeUser(@RequestBody UserRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.removeUser(request));
	}
	
}
