package com.tom.service.userstorage.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.service.userstorage.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	//@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	Optional<User> findByUsername(String username); 

	//@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Optional<User> findByEmail(String email);
	
}
