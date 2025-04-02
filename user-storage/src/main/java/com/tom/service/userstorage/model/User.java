package com.tom.service.userstorage.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {

	private static final long serialVersionUID = 12L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = true, updatable = true)
	private String name;
	
	@Column(name = "username", nullable = false, unique = true, updatable = true)
	private String username;
	
	@Column(name = "email", nullable = false, updatable = true)
	private String email;
	
	@Column(name = "password", nullable = false, updatable = true)
	@JsonIgnore
	private String password;
	
	@Column(name = "date_create", nullable = false, updatable = false)
	private LocalDateTime dateCreated = LocalDateTime.now();
	
	@Column(name = "last_access_time", nullable = true, updatable = true)
	private LocalDateTime lastAccessTime;
	
	/* Using WebSocket Sessions	Real-time tracking	More complex implementation
		make a system to track active users
	*/

	@Enumerated(EnumType.STRING)
	@Column(name = "roles", nullable = false)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private List<Token> tokens;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
