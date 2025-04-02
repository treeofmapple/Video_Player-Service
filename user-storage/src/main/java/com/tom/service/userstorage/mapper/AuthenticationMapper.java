package com.tom.service.userstorage.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.tom.service.userstorage.model.Token;
import com.tom.service.userstorage.model.TokenType;
import com.tom.service.userstorage.model.User;
import com.tom.service.userstorage.request.AuthenticationResponse;
import com.tom.service.userstorage.request.LoginRequest;

@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticationMapper {

	AuthenticationMapper INSTANCE = Mappers.getMapper(AuthenticationMapper.class);	
	
	@Mapping(source= "name", target = "name")
	@Mapping(source= "username", target = "username")
	@Mapping(source= "email", target = "email")
	@Mapping(source= "password", target = "password")
	User buildAttributes(String name, String username, String email, String password);
	
	@Mapping(source= "username", target = "username")
	@Mapping(source= "email", target = "email")
	@Mapping(source= "password", target = "password")
	LoginRequest buildAttributes(String username, String email, String password);
	
	@Mapping(source= "username", target = "username")
	@Mapping(source= "accessToken", target = "accessToken")
	@Mapping(source= "refreshToken", target = "refreshToken")
	AuthenticationResponse buildResponse(String username, String accessToken, String refreshToken);
	
	@Mapping(source= "accessToken", target = "accessToken")
	@Mapping(source= "refreshToken", target = "refreshToken")
	AuthenticationResponse buildResponse(String accessToken, String refreshToken);
	
	@Mapping(source = "user", target = "user")
	@Mapping(source = "token", target = "token")
	@Mapping(source = "tokenType", target = "tokenType")
	@Mapping(source = "revoked", target = "revoked")
	@Mapping(source = "expired", target = "expired")
	@Mapping(source = "user.id", target = "id", qualifiedByName = "mapUuidToString")
	Token buildAttribute(User user, String token, TokenType tokenType, boolean revoked, boolean expired);
	
	@Named("mapUuidToString")
	default String mapUuidToString(UUID value) {
		return (value != null) ? value.toString() : null;
	}
	
}
