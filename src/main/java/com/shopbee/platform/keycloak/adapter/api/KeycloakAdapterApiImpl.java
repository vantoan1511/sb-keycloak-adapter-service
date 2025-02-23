/*
 * MyLibrary.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.api;

import com.shopbee.platform.keycloak.adapter.service.KeycloakAdapterService;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.api.RealmsApi;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.CreateUserRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UpdateUserByIdRequest;
import com.shopbee.sb.keycloak.adapter.service.api.spec.v0.dto.UserDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * The type Keycloak adapter service api.
 */
public class KeycloakAdapterApiImpl implements RealmsApi {

    private final KeycloakAdapterService keycloakAdapterService;
    private final UriInfo uriInfo;

    @Inject
    public KeycloakAdapterApiImpl(KeycloakAdapterService keycloakAdapterService, UriInfo uriInfo) {
        this.keycloakAdapterService = keycloakAdapterService;
        this.uriInfo = uriInfo;
    }

    @Override
    public Response createUser(String realmName, @Valid CreateUserRequest createUserRequest) {
        UserDTO createdUser = keycloakAdapterService.createUser(realmName, createUserRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(createdUser.getId()).build();
        return Response.created(location).build();
    }

    @Override
    public Response deleteUserById(String realmName, String userId) {
        keycloakAdapterService.deleteUserById(realmName, userId);
        return Response.noContent().build();
    }

    @Override
    public Response getUserById(String realmName, String userId) {
        return Response.ok(keycloakAdapterService.getUserById(realmName, userId)).build();
    }

    @Override
    public Response getUsers(String realmName) {
        return Response.ok(keycloakAdapterService.getUsers(realmName)).build();
    }

    @Override
    public Response updateUserById(String realmName, String userId, @Valid UpdateUserByIdRequest updateUserByIdRequest) {
        keycloakAdapterService.updateUserById(realmName, userId, updateUserByIdRequest);
        return Response.ok().build();
    }
}
