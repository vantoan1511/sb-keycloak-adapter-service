/*
 * MyLibrary.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.keycloak.adapter.service.impl;

import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.api.RealmsApi;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UpdateUserByIdRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * The type Keycloak adapter service api.
 */
@Path("/realms/{realmName}")
public class KeycloakAdapterServiceApiImpl implements RealmsApi {

    @Override
    public Response createUser(String realmName, com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.@Valid CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public Response deleteUserById(String realmName, String userId) {
        return null;
    }

    @Override
    public Response getUserById(String realmName, String userId) {
        return null;
    }

    @Override
    public Response getUsers(String realmName) {
        return null;
    }

    @Override
    public Response updateUserById(String realmName, String userId, @Valid UpdateUserByIdRequest updateUserByIdRequest) {
        return null;
    }
}
