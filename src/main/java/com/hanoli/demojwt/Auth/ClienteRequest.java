package com.hanoli.demojwt.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
	private Long id;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String direccion;
	private String telefono;
	private String username;
	private String password;
	private String rol;
	private String email;
}
