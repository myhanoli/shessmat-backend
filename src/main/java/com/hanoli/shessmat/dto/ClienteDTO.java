package com.hanoli.shessmat.dto;

import java.time.LocalDate;
import java.util.List;

import com.hanoli.demojwt.entity.Folio;

public class ClienteDTO {
	
	private Long id;
	private String numCliente;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String telefono;
	private String correo;
	private String direccion;
	private LocalDate fechaAlta;
	
	
	
	public ClienteDTO(Long id, String numCliente, String nombre, String apellidoPat, String apellidoMat,
			String telefono, String correo, String direccion, LocalDate fechaAlta) {
		this.id = id;
		this.numCliente = numCliente;
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		
	}
	
	
	
	




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPat() {
		return apellidoPat;
	}
	public void setApellidoPat(String apellidoPat) {
		this.apellidoPat = apellidoPat;
	}
	public String getApellidoMat() {
		return apellidoMat;
	}
	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	

}
