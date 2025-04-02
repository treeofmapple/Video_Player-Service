package com.tom.service.userstorage.service;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tom.service.userstorage.exception.IncorrectPasswordException;
import com.tom.service.userstorage.exception.InvalidPasswordException;
import com.tom.service.userstorage.exception.PasswordUpdateException;
import com.tom.service.userstorage.model.User;
import com.tom.service.userstorage.repository.UserRepository;
import com.tom.service.userstorage.request.ChangePasswordRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;
	private final AuthenticationService service;

	@Transactional
	public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
		var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
		log.info("User: {}, is changing password", user.getUsername());
		if(!passwordEncoder.matches(request.confirmationPassword(), user.getPassword())) {
			log.warn("User: {}, got an invalid password", user.getUsername());
			throw new InvalidPasswordException(String.format("Invalid Password"));
		}
		if(!request.newPassword().equals(request.confirmationPassword())) {
			log.warn("User: {}, got an incorrect password", user.getUsername());
			throw new IncorrectPasswordException(String.format("Incorrect Password"));
		}
		
		try {
			var password = passwordEncoder.encode(request.newPassword());
			user.setPassword(password);
			repository.save(user);
			service.revokeAllUserTokens(user);
			SecurityContextHolder.clearContext();
			log.info("User: {}, successfully changed the password", user.getUsername());
		} catch (Exception e) {
			log.warn("Failed to update password for User ID: {}", user.getUsername(), e);
			throw new PasswordUpdateException("An error occurred while updating the password. Please try again.");
		}
	}
}
