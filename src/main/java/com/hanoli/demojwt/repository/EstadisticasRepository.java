package com.hanoli.demojwt.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hanoli.demojwt.entity.Folio;
import com.hanoli.shessmat.dto.FoliosPorMesDTO;
import com.hanoli.shessmat.dto.RankingClientesDTO;

public interface EstadisticasRepository extends JpaRepository<Folio, Long> {
	
	   @Query("""
		        SELECT COUNT(f) 
		        FROM Folio f 
		        WHERE YEAR(f.fecha) = :anio
		    """)
		    Long contarFoliosPorAnio(@Param("anio") int anio);
	   
	   
	   @Query("""
		        SELECT new com.hanoli.shessmat.dto.FoliosPorMesDTO(
		            MONTH(f.fecha),
		            COUNT(f)
		        )
		        FROM Folio f
		        WHERE f.fecha BETWEEN :inicio AND :fin
		        GROUP BY MONTH(f.fecha)
		        ORDER BY MONTH(f.fecha)
		    """)
		    List<FoliosPorMesDTO> contarFoliosPorMes(
		        @Param("inicio") LocalDate inicio,
		        @Param("fin") LocalDate fin
		    );

	   
	   
	   @Query("""
		        SELECT new com.hanoli.shessmat.dto.RankingClientesDTO(
		            c.id,
		            CONCAT(c.nombre, ' ', c.apellidoPat),
		            COUNT(f)
		        )
		        FROM Folio f
		        JOIN f.cliente c
		        GROUP BY c.id, c.nombre, c.apellidoPat
		        ORDER BY COUNT(f) DESC
		    """)
		    List<RankingClientesDTO> rankingClientes();
	   
	   @Query("""
		        SELECT new com.hanoli.shessmat.dto.RankingClientesDTO(
		            c.numCliente,
		            CONCAT(c.nombre, ' ', c.apellidoPat),
		            COUNT(f)
		        )
		        FROM Folio f
		        JOIN f.cliente c
		        GROUP BY c.numCliente, c.nombre, c.apellidoPat
		        ORDER BY COUNT(f) DESC
		    """)
		    Page<RankingClientesDTO> rankingClientes(Pageable pageable);
}
