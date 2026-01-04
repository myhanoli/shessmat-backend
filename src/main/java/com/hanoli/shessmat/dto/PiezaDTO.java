package com.hanoli.shessmat.dto;

import java.math.BigDecimal;

public class PiezaDTO {
	
	private String descripcion;
    private BigDecimal costo;
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
    
    

}
