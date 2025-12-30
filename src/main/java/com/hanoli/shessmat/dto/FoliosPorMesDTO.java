package com.hanoli.shessmat.dto;

public class FoliosPorMesDTO {
	
	private int mes;      // 1–12
    private Long total;

    public FoliosPorMesDTO(int mes, Long total) {
        this.mes = mes;
        this.total = total;
    }

    public int getMes() {
        return mes;
    }

    public Long getTotal() {
        return total;
    }

}
