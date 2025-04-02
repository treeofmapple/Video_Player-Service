package com.tom.service.userstorage.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.userstorage.request.AuthenticationResponse;
import com.tom.service.userstorage.request.LoginRequest;
import com.tom.service.userstorage.request.RegisterRequest;
import com.tom.service.userstorage.service.AuthenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "UrlSystem", description = "Operation of URL Shortening")
public class AuthenticationController {
	
	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.authenticate(request));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity <?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.refreshToken(request, response);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
