package com.hanoli.shessmat.dto;

import java.time.LocalDate;
import java.util.Date;

import com.hanoli.demojwt.entity.Folio;

public class FolioResponseDTO {

	

    private Long id;
    private String folio;
   // private Date fecha;
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

    // Datos de cliente
    private Long clienteId;
    private String clienteNombre;
    
    private EstatusDTO estatusActual;
    
    public FolioResponseDTO() {
    }


    public FolioResponseDTO(Folio folio) {
        this.id = folio.getId();
        this.folio = folio.getFolio();
        this.fecha = folio.getFecha();
        this.tipoEquipo = folio.getTipoEquipo();
        this.marca = folio.getMarca();
        this.modelo = folio.getModelo();
        this.numSerie = folio.getNumSerie();
        this.comentarios = folio.getComentarios();
        this.encendido = folio.getEncendido();
        this.traeCargador = folio.getTraeCargador();
        this.marcaCargador = folio.getMarcaCargador();
        this.numSerieCargador = folio.getNumSerieCargador();

        if (folio.getCliente() != null) {
            this.clienteId = folio.getCliente().getId();
            this.clienteNombre = folio.getCliente().getNombre();
        }
        
        if (folio.getEstatusActual() != null) {
            this.estatusActual = new EstatusDTO(folio.getEstatusActual());
        }
    }

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

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}
	
	 public EstatusDTO getEstatusActual() {
	        return estatusActual;
	    }

	    public void setEstatusActual(EstatusDTO estatusActual) {
	        this.estatusActual = estatusActual;
	    }
    
    
	
}
