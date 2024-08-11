package com.letscodemom.controllers;

import com.letscodemom.entities.UserEntity;
import com.letscodemom.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

@Path("/users") // es un rest controller
@Consumes(MediaType.APPLICATION_JSON) // recibe Json
@Produces(MediaType.APPLICATION_JSON) // retorna Json
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @POST
    @Transactional // para interactuar con la DB con POST-PUT-DELETE (no hace falta en GET)
    public Response createUser(UserEntity userEntity){ // ya toma el req body y mapea la entidad
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);

        return Response.ok(users).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID userId) {
        return Response.ok(userService.findById(userId)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional // para interactuar con la DB con POST-PUT-DELETE (no hace falta en GET)
    public Response updateUser(@PathParam("id") UUID userId, UserEntity userEntity){ // ya toma el req body y mapea la entidad
        return Response.ok(userService.updateUser(userId, userEntity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional // para interactuar con la DB con POST-PUT-DELETE (no hace falta en GET)
    public Response deleteUserById(@PathParam("id") UUID userId){
        userService.deleteUser(userId);
        return Response.noContent().build();
    }

}
