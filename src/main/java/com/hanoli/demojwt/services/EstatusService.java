package com.hanoli.demojwt.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Estatus;
import com.hanoli.demojwt.repository.EstatusRepository;

@Service
public class EstatusService {
	
	
	  @Autowired
	    private EstatusRepository estatusRepository;
	
	  public List<Estatus> getAllEstatus() {
	        return estatusRepository.findAll();
	    }


}
