package com.hanoli.shessmat.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReporteReparacionesResponseDTO {
    private int totalReparaciones;
    private BigDecimal totalIngresos;
    private BigDecimal totalManoObra;
    private List<ReporteReparacionDTO> data;

    // Constructor vacío
    public ReporteReparacionesResponseDTO() {}

    // Constructor con parámetros
    public ReporteReparacionesResponseDTO(int totalReparaciones, BigDecimal totalIngresos, BigDecimal totalManoObra, List<ReporteReparacionDTO> data) {
        this.totalReparaciones = totalReparaciones;
        this.totalIngresos = totalIngresos;
        this.totalManoObra = totalManoObra;
        this.data = data;
    }

    // Getters y Setters
    public int getTotalReparaciones() {
        return totalReparaciones;
    }

    public void setTotalReparaciones(int totalReparaciones) {
        this.totalReparaciones = totalReparaciones;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalManoObra() {
        return totalManoObra;
    }

    public void setTotalManoObra(BigDecimal totalManoObra) {
        this.totalManoObra = totalManoObra;
    }

    public List<ReporteReparacionDTO> getData() {
        return data;
    }

    public void setData(List<ReporteReparacionDTO> data) {
        this.data = data;
    }
}
