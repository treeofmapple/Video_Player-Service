package com.tom.service.userstorage.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.userstorage.request.ChangePasswordRequest;
import com.tom.service.userstorage.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "UrlSystem", description = "Operation of URL Shortening")
public class UserController {

	private final UserService service;
	
	@PatchMapping
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
		service.changePassword(request, connectedUser);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucessfull password change");
	}
	
}
