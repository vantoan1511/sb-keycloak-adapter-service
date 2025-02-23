/*
 * KeycloakAdapterException.java
 *
 * Copyright by sb-keycloak-adapter-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.platform.keycloak.adapter.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * The type Keycloak adapter exception.
 */
public final class KeycloakAdapterException extends WebApplicationException {

    /**
     * Prevent creation of a new Keycloak adapter exception.
     *
     * @param message the message
     * @param status  the status
     */
    private KeycloakAdapterException(String message, Response.Status status) {
        super(message, status);
    }

    /**
     * Create keycloak adapter exception.
     *
     * @param message the message
     * @return the keycloak adapter exception
     */
    public static KeycloakAdapterException create(String message) {
        return new KeycloakAdapterException(message, Response.Status.SERVICE_UNAVAILABLE);
    }

}
