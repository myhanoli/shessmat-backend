package com.hanoli.demojwt.sevicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.repository.ClienteRepository;
import com.hanoli.demojwt.services.IClienteService;
import com.hanoli.demojwt.User.Role;
import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.entity.Usuario;



@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private ClienteRepository clientesDao;

	@Override
	public List<Cliente> getLista() {
		
		return (List<Cliente>) clientesDao.findAll();
	
	}

	
	@Override
	public Cliente clienteId(Long id) {
		 return clientesDao.findById(id).orElse(null);
	}


	@Override
	public Cliente guardaCliente(Cliente cliente) {
		return clientesDao.save(cliente);
	}


	@Override
	public void Eliminar(Long id) {
		 clientesDao.deleteById(id);
		
	}
	
	public String getUltimoNumCliente() {
        String ultimo = clientesDao.findMaxNumCliente();
        
        // Manejar el caso si no hay clientes registrados (la tabla está vacía)
        if (ultimo == null || ultimo.isEmpty()) {
            // Devolver un valor base para que Angular pueda generar el consecutivo 1
            int añoActual = java.time.Year.now().getValue();
            return "C" + añoActual + "-0000"; 
        }
        return ultimo;
    }

}
