package com.hanoli.shessmat.dto;

import java.math.BigDecimal;
import java.util.List;

public class CierreFolioDTO {

	
	private Boolean usoPiezas;
    private BigDecimal manoObra;
    private BigDecimal total;
    private List<PiezaDTO> piezas;
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
	public List<PiezaDTO> getPiezas() {
		return piezas;
	}
	public void setPiezas(List<PiezaDTO> piezas) {
		this.piezas = piezas;
	}
	
    
    
}
