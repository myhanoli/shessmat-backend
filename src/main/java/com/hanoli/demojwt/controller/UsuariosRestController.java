package com.hanoli.demojwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.sevicesImpl.ClienteServiceImpl;
import com.hanoli.demojwt.sevicesImpl.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsuariosRestController {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioServiceImpl usuariosImpl;

	@GetMapping("/listUser")
	public List<Usuario> getUsuarios(){
		return usuariosImpl.getLista();
	}


	@PostMapping("/saveUser")
	public ResponseEntity<?> guardaUsuario(@RequestBody ClienteRequest cliente ) {

		System.out.println("[Method Save]" );
		Map<String,Object> response = new HashMap<>();

		Usuario clte = Usuario.builder()
				.id(cliente.getId())
				.nombre(cliente.getNombre())
				.apellidoPat(cliente.getApellidoPat())
				.apellidoMat(cliente.getApellidoMat())
				.direccion(cliente.getDireccion())
				.telefono(cliente.getTelefono())
				.username(cliente.getUsername())
				.password(passwordEncoder.encode( cliente.getPassword()))
				.roles(Set.of(cliente.getRol() != null ? cliente.getRol() : "user"))
				.email(cliente.getEmail())
				.build();
		try {
			usuariosImpl.guardaUsuario(clte);
		}catch (Exception e) {
			response.put("mensaje", "Hubo un problema al guardar el cliente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente se guardo con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> actualizaUsuario(@PathVariable Long id,@RequestBody ClienteRequest cliente){
		System.out.println("[Method Update]" );
		System.out.println("IdCliente: " + id);

		Map<String,Object> response = new HashMap<>();

		try {

			Usuario dataClte = usuariosImpl.usuarioId(id);

			if(dataClte == null) {
				System.out.println("Cliente no existe");

				response.put("mensaje", "No se encontro cliente");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

			}else {

				Usuario clte = Usuario.builder()
						.id(cliente.getId())
						.nombre(cliente.getNombre())
						.apellidoPat(cliente.getApellidoPat())
						.apellidoMat(cliente.getApellidoMat())
						.direccion(cliente.getDireccion())
						.telefono(cliente.getTelefono())
						.username(cliente.getUsername())
						.password(passwordEncoder.encode( cliente.getPassword()))
						.roles(Set.of(cliente.getRol() != null ? cliente.getRol() : "user"))
						.email(cliente.getEmail())
						.build();

				try {

					usuariosImpl.updateUsuariobyId(clte);

					System.out.println("El cliente se actualizo con exito");
					response.put("mensaje", "El cliente se actualizo con exito");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

				}catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
					response.put("mensaje", "Hubo un problema al actualizar el cliente ");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}

		}catch (Exception e) {
			response.put("mensaje", "Hubo un problema al buscar el cliente por Id");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/deleteUser/{id}")
	public void eliminaUsuario(@PathVariable Long id) {
		System.out.println("[Method Delete]" );
		System.out.println("IdClient:" + id );
		usuariosImpl.Eliminar(id);

	}



}