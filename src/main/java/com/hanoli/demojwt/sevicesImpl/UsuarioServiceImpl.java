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
	
		usuariosDao.updateCliente(
				 cliente.getId(), 
				 cliente.getNombre(), 
				 cliente.getApellidoPat(), 
				 cliente.getApellidoMat(),
				 cliente.getDireccion(),
				 cliente.getTelefono(),
				 cliente.getUsername(),
				 cliente.getPassword()
				 );
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
