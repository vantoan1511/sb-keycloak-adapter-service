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

public interface KeycloakAdapterService {

    List<UserDTO> getUsers(String realmName);

    UserDTO createUser(String realmName, CreateUserRequest createUserRequest);

    UserDTO getUserById(String realmName, String userId);

    void updateUserById(String realmName, String userId, UpdateUserByIdRequest updateUserByIdRequest);

    void deleteUserById(String realmName, String userId);
}
