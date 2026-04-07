package com.hanoli.shessmat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReporteReparacionDTO {
    private LocalDateTime fecha;
    private String folio;
    private String tecnico;
    private BigDecimal total;
    private BigDecimal manoObra;
    private Boolean usoPiezas;

    // Constructor vacío
    public ReporteReparacionDTO() {}

    // Constructor con parámetros
    public ReporteReparacionDTO(LocalDateTime fecha, String folio, String tecnico, BigDecimal total, BigDecimal manoObra, Boolean usoPiezas) {
        this.fecha = fecha;
        this.folio = folio;
        this.tecnico = tecnico;
        this.total = total;
        this.manoObra = manoObra;
        this.usoPiezas = usoPiezas;
    }

    // Getters y Setters
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getManoObra() {
        return manoObra;
    }

    public void setManoObra(BigDecimal manoObra) {
        this.manoObra = manoObra;
    }

    public Boolean getUsoPiezas() {
        return usoPiezas;
    }

    public void setUsoPiezas(Boolean usoPiezas) {
        this.usoPiezas = usoPiezas;
    }
}
