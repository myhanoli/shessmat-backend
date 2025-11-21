package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="folios")
public class Folio implements Serializable{
    
    private static final long serialVersionUID = 4629780573695595838L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String folio;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String numSerie;
    private String comentarios;

    // NUEVOS CAMPOS
    private Boolean encendido;       // Para Laptop
    private Boolean traeCargador;    // Para Laptop
    private String marcaCargador;    // Para Laptop si trae cargador
    private String numSerieCargador; // Para Laptop si trae cargador
    
    @JsonIgnoreProperties(value={"folios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    // ------------------ Getters y Setters ------------------
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

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
}
