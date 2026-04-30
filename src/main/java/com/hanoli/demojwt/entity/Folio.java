package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="folios")
public class Folio implements Serializable{

    private static final long serialVersionUID = 4629780573695595838L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String folio;

    //@Temporal(TemporalType.DATE)
    private LocalDate  fecha;

    // ---------------- DATOS DEL EQUIPO ----------------
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String numSerie;
    private String comentarios;

    // Campos específicos
    private Boolean encendido;
    private Boolean traeCargador;
    private String marcaCargador;
    private String numSerieCargador;


    // ---------------- RELACIONES ----------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;


    // Estatus actual del folio
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Estatus estatusActual;


    // Historial de cambios
    @OneToMany(mappedBy = "folio", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HistorialEstatus> historial;

    // CAMPOS PARA DIAGNÓSTICO
    @Enumerated(EnumType.STRING)
    private ResultadoDiagnostico resultadoDiagnostico;

    private java.time.LocalDateTime fechaDiagnostico;

    // CAMPOS PARA TICKET PDF
    private String rutaTicket;
    private java.time.LocalDateTime fechaTicket;

    // CAMPO DE OBSERVACIONES
    private String observaciones;

    // ------------------ Getters y Setters ------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }



    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getTipoEquipo() { return tipoEquipo; }
    public void setTipoEquipo(String tipoEquipo) { this.tipoEquipo = tipoEquipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getNumSerie() { return numSerie; }
    public void setNumSerie(String numSerie) { this.numSerie = numSerie; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    // NUEVOS CAMPOS
    public Boolean getEncendido() { return encendido; }
    public void setEncendido(Boolean encendido) { this.encendido = encendido; }

    public Boolean getTraeCargador() { return traeCargador; }
    public void setTraeCargador(Boolean traeCargador) { this.traeCargador = traeCargador; }

    public String getMarcaCargador() { return marcaCargador; }
    public void setMarcaCargador(String marcaCargador) { this.marcaCargador = marcaCargador; }

    public String getNumSerieCargador() { return numSerieCargador; }
    public void setNumSerieCargador(String numSerieCargador) { this.numSerieCargador = numSerieCargador; }
    public Estatus getEstatusActual() {
        return estatusActual;
    }
    public void setEstatusActual(Estatus estatusActual) {
        this.estatusActual = estatusActual;
    }
    public List<HistorialEstatus> getHistorial() {
        return historial;
    }
    public void setHistorial(List<HistorialEstatus> historial) {
        this.historial = historial;
    }
    public ResultadoDiagnostico getResultadoDiagnostico() {
        return resultadoDiagnostico;
    }
    public void setResultadoDiagnostico(ResultadoDiagnostico resultadoDiagnostico) {
        this.resultadoDiagnostico = resultadoDiagnostico;
    }
    public java.time.LocalDateTime getFechaDiagnostico() {
        return fechaDiagnostico;
    }
    public void setFechaDiagnostico(java.time.LocalDateTime fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }
    public String getRutaTicket() {
        return rutaTicket;
    }
    public void setRutaTicket(String rutaTicket) {
        this.rutaTicket = rutaTicket;
    }
    public java.time.LocalDateTime getFechaTicket() {
        return fechaTicket;
    }
    public void setFechaTicket(java.time.LocalDateTime fechaTicket) {
        this.fechaTicket = fechaTicket;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }



}