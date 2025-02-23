package com.shopbee.platform.keycloak.adapter.exception.mapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.shopbee.platform.keycloak.adapter.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonExceptionMapper implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException e) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(new ErrorResponse("Invalid JSON format: " + e.getMessage()))
            .build();
    }
}
