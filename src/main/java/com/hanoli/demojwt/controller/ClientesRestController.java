package com.hanoli.demojwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanoli.demojwt.Auth.ClienteRequest;
import com.hanoli.demojwt.User.Role;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.sevicesImpl.ClienteServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientesRestController {

	@Autowired
	private ClienteServiceImpl clientesImpl;
	
	
	@GetMapping("/lista")
	public List<Cliente> getClientes(){
		return clientesImpl.getLista();
	}
	
	
	@GetMapping("/idCliente/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Long id){
		Cliente cliente = clientesImpl.clienteId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		if(cliente == null) {
			response.put("mensaje", "El Id del cliente no existe");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(cliente, HttpStatus.OK); 
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardaCliente(@RequestBody Cliente cliente ) {
			
		
		Map<String,Object> response = new HashMap<>();
		
		try {
			clientesImpl.guardaCliente(cliente);	
		}catch (Exception e) {
			response.put("mensaje", "Hubo un problema al guardar el cliente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente se guardo con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualiza(@RequestBody Cliente cliente, @PathVariable Long id){
		
		Cliente clte = clientesImpl.clienteId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		if(clte == null) {
			
			
			response.put("mensaje", "El Id del cliente no existe");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			try {
				
				clte.setNombre(cliente.getNombre());
				clte.setApellidoPat(cliente.getApellidoPat());
				clte.setApellidoMat(cliente.getApellidoMat());
				clte.setDireccion(cliente.getDireccion());
				clte.setTelefono(cliente.getTelefono());
				clte.setCorreo(cliente.getCorreo());
				
				clientesImpl.guardaCliente(clte);
				
			}catch (Exception e) {
				response.put("mensaje", "Hubo un problema al actualizar el cliente");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "El cliente se actualizo con exito");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		
		clientesImpl.Eliminar(id);
		
	}
	
	
	@GetMapping("/ultimo-numero")
    public String getUltimoNumCliente() {
        return clientesImpl.getUltimoNumCliente();
    }
	
	
}
