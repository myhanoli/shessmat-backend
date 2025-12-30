package com.hanoli.shessmat.dto;

import com.hanoli.demojwt.entity.Estatus;

public class EstatusDTO {
	
	private Long id;
    private String nombre;

    public EstatusDTO() {}

    public EstatusDTO(Estatus estatus) {
        this.id = estatus.getId();
        this.nombre = estatus.getNombre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
