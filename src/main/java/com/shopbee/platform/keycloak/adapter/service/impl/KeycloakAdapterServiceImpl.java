/*
 * KeycloakAdapterServiceImpl.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.service.impl;

import com.shopbee.platform.keycloak.adapter.exception.KeycloakAdapterException;
import com.shopbee.platform.keycloak.adapter.mapper.UserMapper;
import com.shopbee.platform.keycloak.adapter.service.KeycloakAdapterService;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.CreateUserRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UpdateUserByIdRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

/**
 * The type Keycloak adapter service.
 */
@ApplicationScoped
public class KeycloakAdapterServiceImpl implements KeycloakAdapterService {

    private final Keycloak keycloak;
    private final UserMapper userMapper;

    /**
     * Instantiates a new Keycloak adapter service.
     *
     * @param keycloak   the keycloak
     * @param userMapper the user mapper
     */
    @Inject
    public KeycloakAdapterServiceImpl(Keycloak keycloak,
                                      UserMapper userMapper) {
        this.keycloak = keycloak;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getUsers(String realmName) {
        return userMapper.from(getUsersResource(realmName).list());
    }

    @Override
    public UserDTO createUser(String realmName, CreateUserRequest createUserRequest) {
        try (Response response = getUsersResource(realmName).create(userMapper.from(createUserRequest))) {
            if (response.getStatus() != 201) {
                throw KeycloakAdapterException.create("Failed to create user. Root cause: " + response.getStatusInfo() + response.readEntity(String.class));
            }

            String location = response.getLocation().getPath();
            String userId = location.substring(location.lastIndexOf('/') + 1);
            return getUserById(realmName, userId);
        }
    }

    @Override
    public UserDTO getUserById(String realmName, String userId) {
        try {
            UserRepresentation userRepresentation = getUserResource(realmName, userId).toRepresentation();
            return userMapper.from(userRepresentation);
        } catch (Exception e) {
            throw KeycloakAdapterException.create("Failed to get user. Root cause: " + e.getMessage());
        }
    }

    @Override
    public void updateUserById(String realmName, String userId, UpdateUserByIdRequest updateUserByIdRequest) {
        try {
            UserRepresentation userRepresentation = getUserResource(realmName, userId).toRepresentation();
            userMapper.update(userRepresentation, updateUserByIdRequest);
            getUserResource(realmName, userId).update(userRepresentation);
        } catch (Exception e) {
            throw KeycloakAdapterException.create("Failed to update user. Root cause: " + e.getMessage());
        }
    }

    @Override
    public void deleteUserById(String realmName, String userId) {
        try {
            getUserResource(realmName, userId).remove();
        } catch (Exception e) {
            throw KeycloakAdapterException.create("Failed to delete user. Root cause: " + e.getMessage());
        }
    }

    /**
     * Gets user resource.
     *
     * @param realmName the realm name
     * @param userId    the user id
     * @return the user resource
     */
    private UserResource getUserResource(String realmName, String userId) {
        return getUsersResource(realmName).get(userId);
    }

    /**
     * Gets users resource.
     *
     * @param realmName the realm name
     * @return the users resource
     */
    private UsersResource getUsersResource(String realmName) {
        return keycloak.realm(realmName).users();
    }
}
