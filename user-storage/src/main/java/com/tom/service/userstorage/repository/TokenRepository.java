package com.tom.service.userstorage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tom.service.userstorage.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query("""
			    SELECT t FROM Token t
			    WHERE t.user.id = :id AND t.expired = false AND t.revoked = false
			""")
	//@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	List<Token> findAllValidTokensByUser(@Param("id") UUID id);

	//@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	Optional<Token> findByToken(String token);
}
