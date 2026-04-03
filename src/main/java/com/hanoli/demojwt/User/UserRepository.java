package com.hanoli.demojwt.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hanoli.demojwt.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByUsername(String username); 
    
    @Modifying()
    @Query("update Usuario u set u.nombre=:nombre, u.apellidoPat=:apellidoPat, u.apellidoMat=:apellidoMat where u.id = :id")
    void updateUser(@Param(value = "id") Integer id,   @Param(value = "nombre") String nombre, @Param(value = "apellidoPat") String apellidoPat, @Param(value = "apellidoMat") String apellidoMat);

}
