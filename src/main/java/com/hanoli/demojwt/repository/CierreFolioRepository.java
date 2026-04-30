package com.hanoli.demojwt.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hanoli.demojwt.entity.CierreFolio;

@Repository
public interface CierreFolioRepository extends JpaRepository<CierreFolio, Long> {

    // Saber si un folio ya tiene cierre
    Optional<CierreFolio> findByFolioId(Long folioId);

    // Verificar si ya existe un cierre para un folio
    boolean existsByFolioId(Long folioId);

    @Query("SELECT cf FROM CierreFolio cf WHERE cf.fechaCierre BETWEEN :startDate AND :endDate")
    List<CierreFolio> findByFechaCierreBetween(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);
}