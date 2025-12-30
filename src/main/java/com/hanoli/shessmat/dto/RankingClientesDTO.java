package com.hanoli.shessmat.dto;

public class RankingClientesDTO {
	
	  private String numCliente;
	    private String clienteNombre;
	    private Long totalFolios;

	    public RankingClientesDTO(String numCliente, String clienteNombre, Long totalFolios) {
	        this.numCliente = numCliente;
	        this.clienteNombre = clienteNombre;
	        this.totalFolios = totalFolios;
	    }

	  
	    public String getNumCliente() {
			return numCliente;
		}

		public String getClienteNombre() {
	        return clienteNombre;
	    }

	    public Long getTotalFolios() {
	        return totalFolios;
	    }

}
