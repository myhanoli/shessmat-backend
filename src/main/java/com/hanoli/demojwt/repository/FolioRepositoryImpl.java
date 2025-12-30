/**
 * 
 */
package com.hanoli.demojwt.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.services.FolioServiceCustom;
import com.hanoli.shessmat.dto.FolioResponseDTO;
import com.hanoli.shessmat.dto.FolioFiltrosDTO;



/**
 * @author hanoli
 *
 */
@Repository
public class FolioRepositoryImpl implements FolioServiceCustom {

	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<Folio> getByFiltros(FolioFiltrosDTO folioFiltrosDTO) {
		String folio = folioFiltrosDTO.getFolio();
		String tipoEquipo = folioFiltrosDTO.getTipoEquipo();
        String marca = folioFiltrosDTO.getMarca();
        String modelo = folioFiltrosDTO.getModelo();
        Date fechaInicio = folioFiltrosDTO.getFechaInicio();
        Date fechaFin = folioFiltrosDTO.getFechaFin();

        System.out.println("folio in getByFiltros: " + folio);
        System.out.println("tipoEquipo in getByFiltros: " + tipoEquipo);
        System.out.println("marca in getByFiltros: " + marca);
        System.out.println("modelo in getByFiltros: " + modelo);
        System.out.println("fechaInicio in getByFiltros: " + fechaInicio);
        System.out.println("fechaFin in getByFiltros: " + fechaFin);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Folio> cq = cb.createQuery(Folio.class);
        Root<Folio> data = cq.from(Folio.class);
        List<Predicate> predicates = new ArrayList<>();

        if (folio != null) {
             predicates.add(cb.equal(data.get("folio"), folio));
        }
        if (tipoEquipo != null) {
            predicates.add(cb.equal(data.get("tipoEquipo"), tipoEquipo));
        }
        
        if (marca != null) {
            predicates.add(cb.equal(data.get("marca"), marca));
       }
       if (modelo != null) {
           predicates.add(cb.equal(data.get("modelo"), modelo));
       }
        
        if (fechaInicio != null || fechaFin != null ) {
            predicates.add(cb.between(data.get("fecha"), fechaInicio, fechaFin));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
	}

	

}
