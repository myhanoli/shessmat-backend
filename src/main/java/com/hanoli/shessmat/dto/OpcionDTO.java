package com.hanoli.shessmat.dto;

public class OpcionDTO {

    private Long value;
    private String label;

    public OpcionDTO(Long value, String label) {
        this.value = value;
        this.label = label;
    }

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

    
}
