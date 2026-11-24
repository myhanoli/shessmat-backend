package com.hanoli.demojwt.sevicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hanoli.demojwt.repository.UsuarioRepository;
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.services.IUsuarioService;


@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository usuariosDao;

	@Override
	public List<Usuario> getLista() {
		
		return (List<Usuario>) usuariosDao.findAll();
	
	}

	@Override
	public Usuario usuarioId(Long id) {	
		 return usuariosDao.findById(id).orElse(null);
	}
	
	@Override
	public void updateUsuariobyId(Usuario cliente) {
		Usuario existente = usuariosDao.findById(cliente.getId()).orElseThrow();
		existente.setNombre(cliente.getNombre());
		existente.setApellidoPat(cliente.getApellidoPat());
		existente.setApellidoMat(cliente.getApellidoMat());
		existente.setDireccion(cliente.getDireccion());
		existente.setTelefono(cliente.getTelefono());
		existente.setUsername(cliente.getUsername());
		existente.setPassword(cliente.getPassword());
		existente.setRoles(cliente.getRoles());
		existente.setIdCliente(cliente.getIdCliente());
		existente.setEmail(cliente.getEmail());
		usuariosDao.save(existente);
	}
	
	@Override
	public Usuario guardaUsuario(Usuario cliente) {
		return usuariosDao.save(cliente);
	}
	
	
	@Override
	public void Eliminar(Long id) {
		usuariosDao.deleteById(id);
		
	}

}
