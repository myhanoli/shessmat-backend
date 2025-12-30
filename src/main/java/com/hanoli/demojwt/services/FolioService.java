package com.hanoli.demojwt.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.entity.Estatus;
import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.entity.FoliosAprobados;
import com.hanoli.demojwt.entity.HistorialEstatus;
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.repository.ClienteRepository;
import com.hanoli.demojwt.repository.EstatusRepository;
import com.hanoli.demojwt.repository.FolioRepository;
import com.hanoli.demojwt.repository.FoliosAprobadosRepository;
import com.hanoli.demojwt.repository.HistorialEstatusRepository;
import com.hanoli.shessmat.dto.FolioResponseDTO;
import com.hanoli.shessmat.dto.HistorialEstatusDTO;
import com.hanoli.shessmat.dto.SeguimientoFolioDTO;
import com.hanoli.shessmat.dto.FolioFiltrosDTO;
import com.hanoli.shessmat.dto.FolioRequestDTO;



@Service
public class FolioService {
	
	@Autowired
    FolioRepository folioRepository;
	
	@Autowired
	FoliosAprobadosRepository foliosAprobadosRepository;
	
	@Autowired
	EstatusRepository estatusRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	HistorialEstatusRepository historialEstatusRepository;
	
	
	
	
	/*public List<Folio> getLista(){
		System.out.println("Voy a obtener los folios");
	//	return folioRepository.findAll();
		 List<Folio> folios = folioRepository.findAll();
		    
		    folios.sort(
		            Comparator.comparing(
		                Folio::getFecha,
		                Comparator.nullsLast(Comparator.naturalOrder())
		            ).reversed()
		        );
		    
		    return folios;
	}*/
	public List<FolioResponseDTO> getLista() {
	    List<Folio> folios = folioRepository.findAll();
	    folios.sort(
	        Comparator.comparing(Folio::getFecha, Comparator.nullsLast(Comparator.naturalOrder()))
	               .reversed()
	    );
	    // Mapear a DTO
	    return folios.stream()
	                 .map(FolioResponseDTO::new)
	                 .toList();
	}

	
	public List<FoliosAprobados> getListaAprobados(){
		return foliosAprobadosRepository.findAll();
	}
	
	public Folio folioId(Long Id) {
		return folioRepository.findById(Id).orElse(null);
	}
	
	/*public Folio guardaFolio(Folio folio) {
		
		  
		// Forzar estatus inicial
		  Estatus estatusRecibido = estatusRepository
	                .findByNombre("RECIBIDO")
	                .orElseThrow(() -> new RuntimeException("Estatus RECIBIDO no existe"));

	        folio.setEstatusActual(estatusRecibido);
	        
	        // 3Guardar folio
	        Folio folioGuardado = folioRepository.save(folio);
	        
	        // 4️⃣ Guardar historial
	        HistorialEstatus historial = new HistorialEstatus();
	        historial.setFolio(folioGuardado);
	        historial.setEstatusAnterior(null);
	        historial.setEstatusNuevo(estatusRecibido);
	        historial.setFechaCambio(new Date());
	        historial.setComentario("Recepción del equipo");

	        historialEstatusRepository.save(historial);
		
		return folioGuardado;
	}*/
	
	public Folio guardaFolio(FolioRequestDTO dto) {

        Folio folio = new Folio();

        // Datos simples
        folio.setFolio(dto.getFolio());
        folio.setFecha(dto.getFecha());
        folio.setTipoEquipo(dto.getTipoEquipo());
        folio.setMarca(dto.getMarca());
        folio.setModelo(dto.getModelo());
        folio.setNumSerie(dto.getNumSerie());
        folio.setComentarios(dto.getComentarios());
        folio.setEncendido(dto.getEncendido());
        folio.setTraeCargador(dto.getTraeCargador());
        folio.setMarcaCargador(dto.getMarcaCargador());
        folio.setNumSerieCargador(dto.getNumSerieCargador());

        // 🔑 Relación Cliente
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        folio.setCliente(cliente);

        // 🔑 Relación Estatus
        Estatus estatusRecibido  = estatusRepository.findById(dto.getIdEstatus())
                .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
        folio.setEstatusActual(estatusRecibido );

        // 4️⃣ Guardar folio primero
        Folio folioGuardado = folioRepository.save(folio);

        // 5️⃣ Guardar historial
        HistorialEstatus historial = new HistorialEstatus();
        historial.setFolio(folioGuardado);
        historial.setEstatusAnterior(null); // Primer estatus
        historial.setEstatusNuevo(estatusRecibido);
        historial.setFechaCambio(LocalDate.now());
        historial.setComentario("Recepción del equipo");

        historialEstatusRepository.save(historial);
        
		return folioGuardado;
    }
	
	public Folio actualizarFolio(Long id, FolioRequestDTO dto) {

	    Folio folio = folioRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Folio no encontrado"));

	    folio.setFolio(dto.getFolio());
	    folio.setFecha(dto.getFecha());
	    folio.setTipoEquipo(dto.getTipoEquipo());
	    folio.setMarca(dto.getMarca());
	    folio.setModelo(dto.getModelo());
	    folio.setNumSerie(dto.getNumSerie());
	    folio.setComentarios(dto.getComentarios());
	    folio.setEncendido(dto.getEncendido());
	    folio.setTraeCargador(dto.getTraeCargador());
	    folio.setMarcaCargador(dto.getMarcaCargador());
	    folio.setNumSerieCargador(dto.getNumSerieCargador());

	    Cliente cliente = clienteRepository.findById(dto.getClienteId())
	            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
	    folio.setCliente(cliente);

	   
	    return folioRepository.save(folio);
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
	
	public List<Folio> getByFiltros(FolioFiltrosDTO folioFiltrosDTO){
		return folioRepository.getByFiltros(folioFiltrosDTO);
	}

	/**
	 * @param id
	 */
	public void Eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	 public Folio actualizarEstatus(SeguimientoFolioDTO dto, Usuario usuario) {
	        Folio folio = folioRepository.findById(dto.getFolioId())
	                .orElseThrow(() -> new RuntimeException("Folio no encontrado"));

	        Estatus nuevoEstatus = estatusRepository.findById(dto.getEstatusId())
	                .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

	        Estatus estatusAnterior = folio.getEstatusActual();

	        // Crear historial
	        HistorialEstatus historial = new HistorialEstatus();
	        historial.setFolio(folio);
	        historial.setEstatusAnterior(estatusAnterior);
	        historial.setEstatusNuevo(nuevoEstatus);
	        historial.setUsuario(usuario); // Usuario que hace el cambio
	        historial.setComentario(dto.getComentario());
	        historial.setFechaCambio(LocalDate.now());
	        
	        historialEstatusRepository.save(historial);

	        // Actualizar estatus actual del folio
	        folio.setEstatusActual(nuevoEstatus);
	        folioRepository.save(folio);

	        return folio;
	    }
	 
	
	 

	    public List<HistorialEstatusDTO> getHistorialPorFolio(Long folioId) {
	    	 List<HistorialEstatusDTO> historial = historialEstatusRepository
	    	            .findByFolioIdOrderByFechaCambioAsc(folioId)
	    	            .stream()
	    	            .map(h -> new HistorialEstatusDTO(
	    	                    h.getId(),
	    	                    h.getEstatusAnterior() != null ? h.getEstatusAnterior().getNombre() : null,
	    	                    h.getEstatusNuevo() != null ? h.getEstatusNuevo().getNombre() : null,
	    	                    h.getUsuario() != null ? h.getUsuario().getNombre() : null,
	    	                    h.getFechaCambio(),
	    	                    h.getComentario()
	    	            ))
	    	            .toList();

	    	    if (historial.isEmpty()) {
	    	        return Collections.emptyList();
	    	    }

	    	    String estatusActual = historial.stream()
	    	            .map(HistorialEstatusDTO::getEstatusNuevo)
	    	            .filter(Objects::nonNull)
	    	            .reduce((first, second) -> second) // último estatusNuevo
	    	            .orElse(null);

	    	    if (estatusActual == null) {
	    	        return Collections.emptyList();
	    	    }

	    	   
	    	    List<HistorialEstatusDTO> avancesDelEstatusActual = historial.stream()
	    	            // solo registros que son avances (estatusAnterior == estatusNuevo)
	    	            .filter(h -> h.getEstatusAnterior() != null && h.getEstatusAnterior().equals(h.getEstatusNuevo()))
	    	            // solo del estatus actual
	    	            .filter(h -> h.getEstatusNuevo().equals(estatusActual))
	    	            // ordenar por fecha (nulls al final)
	    	            .sorted(Comparator.comparing(HistorialEstatusDTO::getFechaCambio,
	    	                    Comparator.nullsLast(Comparator.naturalOrder())))

	    	            .toList();
	    	    
	    	    
	    	    if (avancesDelEstatusActual.isEmpty() && estatusActual != null) {
	    	        HistorialEstatusDTO dummy = new HistorialEstatusDTO();
	    	        dummy.setEstatusAnterior(estatusActual);
	    	        dummy.setEstatusNuevo(estatusActual);
	    	        dummy.setComentario("No hay avances registrados todavía");
	    	        avancesDelEstatusActual = List.of(dummy);
	    	    }


	    	    return avancesDelEstatusActual;
	    	
	    }
	 


	    
}
