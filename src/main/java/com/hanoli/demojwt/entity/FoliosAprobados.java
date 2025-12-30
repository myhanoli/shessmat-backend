package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="foliosaprobados")
public class FoliosAprobados implements Serializable{
	
	private static final long serialVersionUID = 4629780573695595838L;
	
	@Id
	//Configuracion para Oracle
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FOL_SEQ")
	//@SequenceGenerator(name = "FOL_SEQ",sequenceName = "foliosAprobados_seq",initialValue=1, allocationSize = 1 )
	
	//Configuracion para MySQL
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String folio;
	
	//@Temporal(TemporalType.DATE)
	private LocalDate fecha;
	private String tipoEquipo;
	private String marca;
	private String modelo;
	private String numSerie;
	private Integer cliente;
	private Integer estatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	/**
	 * @return the cliente
	 */
	public Integer getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the estatus
	 */
	public Integer getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	

}

