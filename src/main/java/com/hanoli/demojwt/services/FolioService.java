package com.hanoli.demojwt.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.entity.FoliosAprobados;
import com.hanoli.demojwt.repository.FolioRepository;
import com.hanoli.demojwt.repository.FoliosAprobadosRepository;
import com.hanoli.shessmat.dto.FolioDTO;



@Service
public class FolioService {
	
	@Autowired
    FolioRepository folioRepository;
	
	@Autowired
	FoliosAprobadosRepository foliosAprobadosRepository;
	
	public List<Folio> getLista(){
		System.out.println("Voy a obtener los folios");
		return folioRepository.findAll();
	}

	
	public List<FoliosAprobados> getListaAprobados(){
		return foliosAprobadosRepository.findAll();
	}
	
	public Folio folioId(Long Id) {
		return folioRepository.findById(Id).orElse(null);
	}
	
	public Folio guardaFolio(Folio empleado) {
		return folioRepository.save(empleado);
	}
	
	public FoliosAprobados guardaFolioAprobado(FoliosAprobados folio) {
		return foliosAprobadosRepository.save(folio);
	}
	/*public Object Eliminar(Long id) {
		return folioRepository.deleteById(id);
	}*/
	
	public String getEndFolio() {
		return folioRepository.getEndFolio();
	}
	
	public List<Folio> getFolioByMarca(String marca){
		return folioRepository.getFoliByMarca(marca);
	}
	
	public List<Folio> getByFiltros(FolioDTO folioDTO){
		return folioRepository.getByFiltros(folioDTO);
	}

	/**
	 * @param id
	 */
	public void Eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}
}
