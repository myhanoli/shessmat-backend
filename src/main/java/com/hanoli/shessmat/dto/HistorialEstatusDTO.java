package com.hanoli.shessmat.dto;


import java.time.LocalDateTime;

public class HistorialEstatusDTO {
	
	 private Long id;
	    private String estatusAnterior;
	    private String estatusNuevo;
	    private String usuario;
	    private LocalDateTime fechaCambio;
	    private String comentario;

	    
	    public HistorialEstatusDTO() {}
	    
	    // Constructor
	    public HistorialEstatusDTO(Long id, String estatusAnterior, String estatusNuevo, String usuario, LocalDateTime fechaCambio, String comentario) {
	        this.id = id;
	        this.estatusAnterior = estatusAnterior;
	        this.estatusNuevo = estatusNuevo;
	        this.usuario = usuario;
	        this.fechaCambio = fechaCambio;
	        this.comentario = comentario;
	    }

	 

	

		// Getters y Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getEstatusAnterior() { return estatusAnterior; }
	    public void setEstatusAnterior(String estatusAnterior) { this.estatusAnterior = estatusAnterior; }

	    public String getEstatusNuevo() { return estatusNuevo; }
	    public void setEstatusNuevo(String estatusNuevo) { this.estatusNuevo = estatusNuevo; }

	    public String getUsuario() { return usuario; }
	    public void setUsuario(String usuario) { this.usuario = usuario; }

		public String getComentario() { return comentario; }
	    public LocalDateTime getFechaCambio() {
			return fechaCambio;
		}

		public void setFechaCambio(LocalDateTime fechaCambio) {
			this.fechaCambio = fechaCambio;
		}

		public void setComentario(String comentario) { this.comentario = comentario; }

}
