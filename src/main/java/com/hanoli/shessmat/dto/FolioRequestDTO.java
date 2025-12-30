package com.hanoli.shessmat.dto;

import java.time.LocalDate;
import java.util.Date;

public class FolioRequestDTO {
	
	private String folio;
    private LocalDate fecha;

    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String numSerie;
    private String comentarios;

    private Boolean encendido;
    private Boolean traeCargador;
    private String marcaCargador;
    private String numSerieCargador;

    // 🔑 IDs de relaciones
    private Long clienteId;
    private Long idEstatus;
    
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoEquipo() {
		return tipoEquipo;
	}
	public void setTipoEquipo(String tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Boolean getEncendido() {
		return encendido;
	}
	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}
	public Boolean getTraeCargador() {
		return traeCargador;
	}
	public void setTraeCargador(Boolean traeCargador) {
		this.traeCargador = traeCargador;
	}
	public String getMarcaCargador() {
		return marcaCargador;
	}
	public void setMarcaCargador(String marcaCargador) {
		this.marcaCargador = marcaCargador;
	}
	public String getNumSerieCargador() {
		return numSerieCargador;
	}
	public void setNumSerieCargador(String numSerieCargador) {
		this.numSerieCargador = numSerieCargador;
	}
	
	
	
	
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}
    
    
    

}
