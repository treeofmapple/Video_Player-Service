package com.tom.service.userstorage.service;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.service.userstorage.exception.NotFoundException;
import com.tom.service.userstorage.mapper.AuthenticationMapper;
import com.tom.service.userstorage.model.Role;
import com.tom.service.userstorage.model.TokenType;
import com.tom.service.userstorage.model.User;
import com.tom.service.userstorage.repository.TokenRepository;
import com.tom.service.userstorage.repository.UserRepository;
import com.tom.service.userstorage.request.AuthenticationResponse;
import com.tom.service.userstorage.request.LoginRequest;
import com.tom.service.userstorage.request.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService { // TODO: implement logs

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final AuthenticationMapper authenticationMapper;
	private final JwtService jwtService;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = authenticationMapper.buildAttributes(request.name(), request.username(), request.email(),
				passwordEncoder.encode(request.password()));
		user.setRole(Role.USER);
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		var savedUser = userRepository.save(user);
		saveUserToken(savedUser, jwtToken);
		return authenticationMapper.buildResponse(user.getUsername(), jwtToken, refreshToken);
	}

	public AuthenticationResponse authenticate(LoginRequest request) {
		var user = userRepository.findByEmail(request.email()).orElseThrow(
				() -> new NotFoundException(String.format("User with email: %s was not found", request.email())));

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.username().isBlank() ? request.username() : user.getUsername(), request.password()));

		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return authenticationMapper.buildResponse(user.getUsername(), jwtToken, refreshToken);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken, username;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		username = jwtService.extractUsername(refreshToken);
		if (username != null) {
			var user = userRepository.findByUsername(username).orElseThrow(
					() -> new NotFoundException(String.format("User with name: %s, was not found", username)));
			if(jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = authenticationMapper.buildResponse(accessToken, refreshToken);
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

	public void revokeAllUserTokens(User user) {
		var validUserToken = tokenRepository.findAllValidTokensByUser(user.getId());
		if (validUserToken.isEmpty()) {
			return;
		}
		validUserToken.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserToken);
		log.info("Revoked all tokens for User: {}", user.getUsername());
	}
	
	private void saveUserToken(User user, String jwtToken) {
		var token = authenticationMapper.buildAttribute(user, jwtToken, TokenType.BEARER, false, false);
		tokenRepository.save(token);
	}
}
