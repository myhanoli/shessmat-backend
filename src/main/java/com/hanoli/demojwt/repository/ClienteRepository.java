package com.hanoli.demojwt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanoli.demojwt.entity.Cliente;



public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	@Query(value = "SELECT c.numCliente FROM Cliente c ORDER BY c.numCliente DESC LIMIT 1")
    String findMaxNumCliente();
	

}
