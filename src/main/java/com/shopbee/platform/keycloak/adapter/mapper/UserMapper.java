/*
 * UserMapper.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */
package com.shopbee.platform.keycloak.adapter.mapper;

import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.CreateUserRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UpdateUserByIdRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UserDTO;
import java.util.List;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", source = "enabled", qualifiedByName = "toBoolean")
    @Mapping(target = "emailVerified", source = "emailVerified", qualifiedByName = "toBoolean")
    UserRepresentation from(CreateUserRequest createUserRequest);

    List<UserDTO> from(List<UserRepresentation> userRepresentations);

    UserDTO from(UserRepresentation userRepresentation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "enabled", source = "enabled", qualifiedByName = "toBoolean")
    @Mapping(target = "emailVerified", source = "emailVerified", qualifiedByName = "toBoolean")
    void update(@MappingTarget UserRepresentation userRepresentation, UpdateUserByIdRequest updateUserByIdRequest);

    @Named("toBoolean")
    default Boolean toBoolean(Object value) {
        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }
        return null;
    }
}
