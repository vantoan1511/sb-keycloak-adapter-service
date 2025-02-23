/*
 * KeycloakAdapterException.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class KeycloakAdapterException extends WebApplicationException {

    private KeycloakAdapterException(String message, Response.Status status) {
        super(message, status);
    }

    public static KeycloakAdapterException create(String message) {
        return new KeycloakAdapterException(message, Response.Status.SERVICE_UNAVAILABLE);
    }

}
