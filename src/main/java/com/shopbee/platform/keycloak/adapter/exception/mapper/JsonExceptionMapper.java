/*
 * KeycloakAdapterExceptionMapper.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.exception.mapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.shopbee.platform.keycloak.adapter.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The type Json exception mapper.
 * This class is responsible for mapping JsonParseException to a JAX-RS Response.
 */
@Provider
public class JsonExceptionMapper implements ExceptionMapper<JsonParseException> {

    /**
     * Converts a JsonParseException to a JAX-RS Response.
     *
     * @param jsonParseException the JsonParseException to be mapped
     * @return a Response object containing the error message and status code
     */
    @Override
    public Response toResponse(JsonParseException jsonParseException) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(new ErrorResponse("Invalid JSON format: " + jsonParseException.getMessage()))
            .build();
    }
}
