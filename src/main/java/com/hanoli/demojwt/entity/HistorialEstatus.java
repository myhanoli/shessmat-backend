package com.hanoli.demojwt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "historial_estatus")
public class HistorialEstatus {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Folio folio;

    
    @ManyToOne(fetch = FetchType.LAZY)
    private Estatus estatusAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estatus estatusNuevo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaCambio;

    private String comentario;

   

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

	public Estatus getEstatusAnterior() {
		return estatusAnterior;
	}

	public void setEstatusAnterior(Estatus estatusAnterior) {
		this.estatusAnterior = estatusAnterior;
	}

	public Estatus getEstatusNuevo() {
		return estatusNuevo;
	}

	public void setEstatusNuevo(Estatus estatusNuevo) {
		this.estatusNuevo = estatusNuevo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	



	

	/*public LocalDate getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(LocalDate fechaCambio) {
		this.fechaCambio = fechaCambio;
	}*/
	

	public String getComentario() {
		return comentario;
	}

	public LocalDateTime getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(LocalDateTime fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
    


}
