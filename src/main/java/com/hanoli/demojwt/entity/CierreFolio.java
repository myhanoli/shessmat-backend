package com.hanoli.demojwt.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cierre_folio")
public class CierreFolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "folio_id", nullable = false)
    private Folio folio;

    @OneToOne
    @JoinColumn(name = "historial_estatus_id", nullable = false)
    private HistorialEstatus historialEstatus;

    private Boolean usoPiezas;

    private BigDecimal manoObra;

    private BigDecimal total;

    private LocalDateTime fechaCierre;

    @OneToMany(mappedBy = "cierreFolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PiezaReparacion> piezas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Folio getFolio() {
		return folio;
	}

	public void setFolio(Folio folio) {
		this.folio = folio;
	}

	public HistorialEstatus getHistorialEstatus() {
		return historialEstatus;
	}

	public void setHistorialEstatus(HistorialEstatus historialEstatus) {
		this.historialEstatus = historialEstatus;
	}

	public Boolean getUsoPiezas() {
		return usoPiezas;
	}

	public void setUsoPiezas(Boolean usoPiezas) {
		this.usoPiezas = usoPiezas;
	}

	public BigDecimal getManoObra() {
		return manoObra;
	}

	public void setManoObra(BigDecimal manoObra) {
		this.manoObra = manoObra;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	public List<PiezaReparacion> getPiezas() {
		return piezas;
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public void setPiezas(List<PiezaReparacion> piezas) {
		this.piezas = piezas;
	}

    
}