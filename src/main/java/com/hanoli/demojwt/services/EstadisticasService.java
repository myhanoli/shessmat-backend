package com.hanoli.demojwt.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.repository.EstadisticasRepository;
import com.hanoli.shessmat.dto.FoliosPorMesDTO;
import com.hanoli.shessmat.dto.RankingClientesDTO;

@Service
public class EstadisticasService {
	
	  @Autowired
	    private EstadisticasRepository estadisticasRepository;

	    public Long obtenerTotalFoliosPorAnio(int anio) {
	        return estadisticasRepository.contarFoliosPorAnio(anio);
	    }
	    
	    
	    

	    public List<FoliosPorMesDTO> obtenerFoliosPorMes(int anio) {

	      /*  Calendar inicio = Calendar.getInstance();
	        inicio.set(anio, Calendar.JANUARY, 1, 0, 0, 0);
	        inicio.set(Calendar.MILLISECOND, 0);

	        Calendar fin = Calendar.getInstance();
	        fin.set(anio, Calendar.DECEMBER, 31, 23, 59, 59);
	        fin.set(Calendar.MILLISECOND, 999);

	        return estadisticasRepository.contarFoliosPorMes(
	                inicio.getTime(),
	                fin.getTime()
	        );*/
	    	  LocalDate inicio = LocalDate.of(anio, 1, 1);
	    	    LocalDate fin = LocalDate.of(anio, 12, 31);

	    	    return estadisticasRepository.contarFoliosPorMes(inicio, fin);
	    }
	    
	    
	    public List<RankingClientesDTO> obtenerRankingClientes() {
	        return estadisticasRepository.rankingClientes();
	    }
	    
	    public List<RankingClientesDTO> obtenerTop10Clientes() {
	        return estadisticasRepository
	                .rankingClientes(PageRequest.of(0, 10))
	                .getContent();
	    }

}
