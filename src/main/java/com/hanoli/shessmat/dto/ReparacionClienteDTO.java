package com.hanoli.shessmat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReparacionClienteDTO {

    private Long clienteId;
    private Long folioId;
    private String folio;
    private LocalDate fecha;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String comentarios;
    private String estatus;
    private Long estatusId;
    private Integer progreso;
    private BigDecimal cotizacion;
    private Boolean cotizacionAprobada;
    private String ultimoComentario;

    public ReparacionClienteDTO() {
    }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getFolioId() { return folioId; }
    public void setFolioId(Long folioId) { this.folioId = folioId; }
    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipoEquipo() { return tipoEquipo; }
    public void setTipoEquipo(String tipoEquipo) { this.tipoEquipo = tipoEquipo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
    public Long getEstatusId() { return estatusId; }
    public void setEstatusId(Long estatusId) { this.estatusId = estatusId; }
    public Integer getProgreso() { return progreso; }
    public void setProgreso(Integer progreso) { this.progreso = progreso; }
    public BigDecimal getCotizacion() { return cotizacion; }
    public void setCotizacion(BigDecimal cotizacion) { this.cotizacion = cotizacion; }
    public Boolean getCotizacionAprobada() { return cotizacionAprobada; }
    public void setCotizacionAprobada(Boolean cotizacionAprobada) { this.cotizacionAprobada = cotizacionAprobada; }
    public String getUltimoComentario() { return ultimoComentario; }
    public void setUltimoComentario(String ultimoComentario) { this.ultimoComentario = ultimoComentario; }
}
