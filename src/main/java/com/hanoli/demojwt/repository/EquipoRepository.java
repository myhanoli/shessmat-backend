package com.hanoli.demojwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hanoli.demojwt.entity.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}