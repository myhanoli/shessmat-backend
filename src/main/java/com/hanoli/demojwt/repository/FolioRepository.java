package com.hanoli.demojwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.services.FolioServiceCustom;


public interface FolioRepository extends JpaRepository<Folio, Long>, FolioServiceCustom{
	
	/*@Query("SELECT MAX(f.folio) FROM Folio f")
	 String getEndFolio();*/
	
	@Query(value = "SELECT f.folio FROM folios f " +
            "ORDER BY CAST(SUBSTRING(f.folio, 7) AS UNSIGNED) DESC " +
            "LIMIT 1", nativeQuery = true)
String getEndFolio();
	
	
	@Query("SELECT f FROM Folio f where f.marca = ?1")
	 List<Folio> getFoliByMarca(String marca);
	
	

}
