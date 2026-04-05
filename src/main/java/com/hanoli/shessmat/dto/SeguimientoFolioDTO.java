package com.hanoli.shessmat.dto;

public class SeguimientoFolioDTO {

	   private Long folioId;
	    private Long estatusId;
	    private String comentario;

	    private CierreFolioDTO cierre;

	    // NUEVO: Resultado del diagnóstico
	    private String resultadoDiagnostico;

	    // Getters y setters
	    public Long getFolioId() { return folioId; }
	    public void setFolioId(Long folioId) { this.folioId = folioId; }

	    public Long getEstatusId() { return estatusId; }
	    public void setEstatusId(Long estatusId) { this.estatusId = estatusId; }

	    public String getComentario() { return comentario; }
	    public void setComentario(String comentario) { this.comentario = comentario; }
		public CierreFolioDTO getCierre() {
			return cierre;
		}
		public void setCierre(CierreFolioDTO cierre) {
			this.cierre = cierre;
		}
		public String getResultadoDiagnostico() {
			return resultadoDiagnostico;
		}
		public void setResultadoDiagnostico(String resultadoDiagnostico) {
			this.resultadoDiagnostico = resultadoDiagnostico;
		}

	    
}
