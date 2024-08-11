package com.letscodemom.repositories;

import com.letscodemom.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped // inyecta como bean de la app
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {
    // podemos usar los services directamente (active record pattern)
    // o usar el patron repository
    // ver https://github.com/buildrun-tech/buildrun-java-quarkus-examplo/tree/main
}
