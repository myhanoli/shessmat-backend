package com.hanoli.demojwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanoli.demojwt.entity.HistorialEstatus;

public interface HistorialEstatusRepository extends JpaRepository<HistorialEstatus, Long> {
	
	 List<HistorialEstatus> findByFolioIdOrderByFechaCambioAsc(Long folioId);
	
}
