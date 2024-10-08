package com.letscodemom.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException e) {
        // te permite manejar y personalizar la respuesta de tu excepcion
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "User Not Found").build();
    }
}
