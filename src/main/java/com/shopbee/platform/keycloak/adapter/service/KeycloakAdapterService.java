/*
 * KeycloakAdapterService.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.service;

import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.CreateUserRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UpdateUserByIdRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UserDTO;
import java.util.List;

/**
 * The interface KeycloakAdapterService.
 * This interface defines methods for managing users in a Keycloak realm.
 */
public interface KeycloakAdapterService {

    /**
     * Retrieves a list of users from the specified realm.
     *
     * @param realmName the name of the realm
     * @return the list of user DTOs
     */
    List<UserDTO> getUsers(String realmName);

    /**
     * Creates a new user in the specified realm.
     *
     * @param realmName the name of the realm
     * @param createUserRequest the request object containing user details
     * @return the created user DTO
     */
    UserDTO createUser(String realmName, CreateUserRequest createUserRequest);

    /**
     * Retrieves a user by their ID from the specified realm.
     *
     * @param realmName the name of the realm
     * @param userId the ID of the user
     * @return the user DTO
     */
    UserDTO getUserById(String realmName, String userId);

    /**
     * Updates a user by their ID in the specified realm.
     *
     * @param realmName the name of the realm
     * @param userId the ID of the user
     * @param updateUserByIdRequest the request object containing updated user details
     */
    void updateUserById(String realmName, String userId, UpdateUserByIdRequest updateUserByIdRequest);

    /**
     * Deletes a user by their ID from the specified realm.
     *
     * @param realmName the name of the realm
     * @param userId the ID of the user
     */
    void deleteUserById(String realmName, String userId);
}
