package com.hanoli.demojwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanoli.demojwt.entity.Estatus;

public interface EstatusRepository extends JpaRepository<Estatus, Long> {
}