package com.hanoli.demojwt.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pieza_reparacion")
public class PiezaReparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private BigDecimal costo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cierre_folio_id")
    private CierreFolio cierreFolio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public CierreFolio getCierreFolio() {
		return cierreFolio;
	}

	public void setCierreFolio(CierreFolio cierreFolio) {
		this.cierreFolio = cierreFolio;
	}

   
}