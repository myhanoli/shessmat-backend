package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.entity.Usuario;


public interface IClienteService {
	
	public List<Cliente> getLista();

	public Cliente clienteId(Long Id);
	
	public Cliente guardaCliente(Cliente empleado);
	
	public void Eliminar(Long id);
	
	public String getUltimoNumCliente();
	
}
