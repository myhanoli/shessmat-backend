package com.hanoli.demojwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hanoli.demojwt.entity.CierreFolio;

@Repository
public interface CierreFolioRepository extends JpaRepository<CierreFolio, Long> {

    // Saber si un folio ya tiene cierre
    Optional<CierreFolio> findByFolioId(Long folioId);

    // Verificar si ya existe un cierre para un folio
    boolean existsByFolioId(Long folioId);
}

