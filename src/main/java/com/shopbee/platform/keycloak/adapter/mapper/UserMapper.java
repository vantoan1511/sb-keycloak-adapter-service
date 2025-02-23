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

/**
 * The interface UserMapper.
 * This interface defines methods for mapping between different user-related data transfer objects (DTOs) and Keycloak's UserRepresentation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    /**
     * Maps a CreateUserRequest to a UserRepresentation.
     *
     * @param createUserRequest the create user request
     * @return the user representation
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", source = "enabled", qualifiedByName = "toBoolean")
    @Mapping(target = "emailVerified", source = "emailVerified", qualifiedByName = "toBoolean")
    UserRepresentation from(CreateUserRequest createUserRequest);

    /**
     * Maps a list of UserRepresentation to a list of UserDTO. May contain null values.
     *
     * @param userRepresentations the user representations
     * @return the list of user dto
     */
    List<UserDTO> from(List<UserRepresentation> userRepresentations);

    /**
     * Maps a UserRepresentation to a UserDTO.
     *
     * @param userRepresentation the user representation
     * @return the user dto
     */
    UserDTO from(UserRepresentation userRepresentation);

    /**
     * Updates a UserRepresentation using an UpdateUserByIdRequest.
     *
     * @param userRepresentation    the user representation
     * @param updateUserByIdRequest the update user by id request
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "enabled", source = "enabled", qualifiedByName = "toBoolean")
    @Mapping(target = "emailVerified", source = "emailVerified", qualifiedByName = "toBoolean")
    void update(@MappingTarget UserRepresentation userRepresentation, UpdateUserByIdRequest updateUserByIdRequest);

    /**
     * Converts an object to a Boolean.
     *
     * @param value the value
     * @return the boolean
     */
    @Named("toBoolean")
    default Boolean toBoolean(Object value) {
        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }
        return null;
    }
}
