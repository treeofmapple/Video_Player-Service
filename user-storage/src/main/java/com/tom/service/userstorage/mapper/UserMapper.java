package com.tom.service.userstorage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.tom.service.userstorage.model.User;
import com.tom.service.userstorage.request.DeleteResponse;
import com.tom.service.userstorage.request.RoleResponse;

@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);	
	
	
	@Mapping(source= "username", target = "username")
	@Mapping(source= "email", target = "email")
	@Mapping(source= "role", target = "role")
	RoleResponse buildRoleResponse(User user);

	@Mapping(source= "username", target = "username")
	@Mapping(source= "email", target = "email")
	DeleteResponse buildDeleteResponse(User user);

}
