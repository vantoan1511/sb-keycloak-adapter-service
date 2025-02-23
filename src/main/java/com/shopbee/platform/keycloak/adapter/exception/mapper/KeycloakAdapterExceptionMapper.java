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

/**
 * The type Keycloak adapter exception mapper.
 * This class is responsible for mapping KeycloakAdapterException to a JAX-RS Response.
 */
@Provider
public class KeycloakAdapterExceptionMapper implements ExceptionMapper<KeycloakAdapterException> {

    /**
     * Converts a KeycloakAdapterException to a JAX-RS Response.
     *
     * @param keycloakAdapterException the KeycloakAdapterException to be mapped
     * @return a Response object containing the error message and status code
     */
    @Override
    public Response toResponse(KeycloakAdapterException keycloakAdapterException) {
        ErrorResponse errorResponse = new ErrorResponse(keycloakAdapterException.getMessage());
        return Response.status(keycloakAdapterException.getResponse().getStatus()).entity(errorResponse).build();
    }
}
