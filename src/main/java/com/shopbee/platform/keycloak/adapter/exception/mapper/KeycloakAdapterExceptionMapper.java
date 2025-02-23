/*
 * KeycloakAdapterExceptionMapper.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.exception.mapper;

import com.shopbee.platform.keycloak.adapter.exception.ErrorResponse;
import com.shopbee.platform.keycloak.adapter.exception.KeycloakAdapterException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class KeycloakAdapterExceptionMapper implements ExceptionMapper<KeycloakAdapterException> {

    @Override
    public Response toResponse(KeycloakAdapterException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return Response.status(e.getResponse().getStatus()).entity(errorResponse).build();
    }
}
