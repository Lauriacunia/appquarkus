package com.letscodemom.services;

import com.letscodemom.entities.UserEntity;
import com.letscodemom.exceptions.UserNotFoundException;
import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;


@ApplicationScoped // es como @Service de springboot
public class UserService {

    public UserEntity createUser (UserEntity userEntity){
        UserEntity.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll()
                             .page(page, pageSize)
                             .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) UserEntity.findByIdOptional(userId) //castea response a UserEntity
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var userToUpdate = findById(userId); // busco el user de la DB
        // si no lo encuentra el metodo retorna 404, sino continua:
        userToUpdate.username = userEntity.username; // sobrescribo el username
        UserEntity.persist(userToUpdate); // salvo el usaurio de la DB
        return userToUpdate;
    }

    public void deleteUser(UUID userId) {
        var userToDelete = findById(userId); // busco el user de la DB
        // si no lo encuentra el metodo retorna 404, sino continua:
        UserEntity.deleteById(userId);
    }
}
