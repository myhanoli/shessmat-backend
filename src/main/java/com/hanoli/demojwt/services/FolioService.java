package com.hanoli.demojwt.services;

import java.math.BigDecimal;
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

import com.hanoli.demojwt.entity.CierreFolio;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.entity.Estatus;
import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.entity.FoliosAprobados;
import com.hanoli.demojwt.entity.HistorialEstatus;
import com.hanoli.demojwt.entity.PiezaReparacion;
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.entity.ResultadoDiagnostico;
import com.hanoli.demojwt.repository.CierreFolioRepository;
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
	
	@Autowired
	CierreFolioRepository cierreFolioRepository;
	
	@Autowired
	TicketPdfService ticketPdfService;


	public List<FolioResponseDTO> getLista() {
	    List<Folio> folios = folioRepository.findAll();
	    folios.sort(
	        Comparator.comparing(Folio::getFecha, Comparator.nullsLast(Comparator.naturalOrder()))
	               .reversed()
	    );

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

        //Relación Cliente
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        folio.setCliente(cliente);

        //Relación Estatus
        Estatus estatusRecibido  = estatusRepository.findById(dto.getIdEstatus())
                .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
        folio.setEstatusActual(estatusRecibido );

        //Guardar folio primero
        Folio folioGuardado = folioRepository.save(folio);

        // 5️⃣ Guardar historial
        HistorialEstatus historial = new HistorialEstatus();
        historial.setFolio(folioGuardado);
        historial.setEstatusAnterior(null); // Primer estatus
        historial.setEstatusNuevo(estatusRecibido);
        historial.setFechaCambio(LocalDateTime.now());
        historial.setComentario(dto.getComentarios());

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

	    //No permitir cambios si ya está cerrado
	    if ("CERRADO".equals(folio.getEstatusActual().getNombre())) {
	        throw new IllegalStateException("El folio ya está cerrado");
	    }

	    // Crear historial
	    HistorialEstatus historial = new HistorialEstatus();
	    historial.setFolio(folio);
	    historial.setEstatusAnterior(folio.getEstatusActual());
	    historial.setEstatusNuevo(nuevoEstatus);
	    historial.setUsuario(usuario);
	    historial.setComentario(dto.getComentario());
	    historial.setFechaCambio(LocalDateTime.now());

	    historialEstatusRepository.save(historial);

	    //LÓGICA ESPECIAL DE CIERRE
	    if ("CERRADO".equals(nuevoEstatus.getNombre())) {

	        if (dto.getCierre() == null) {
	            throw new IllegalArgumentException("Información de cierre requerida");
	        }

	        if (dto.getCierre().getTotal() == null ||
	            dto.getCierre().getTotal().compareTo(BigDecimal.ZERO) <= 0) {
	            throw new IllegalArgumentException("El total debe ser mayor a 0");
	        }

	        CierreFolio cierre = new CierreFolio();
	        cierre.setFolio(folio);
	        cierre.setHistorialEstatus(historial);
	        cierre.setUsoPiezas(dto.getCierre().getUsoPiezas());
	        cierre.setManoObra(dto.getCierre().getManoObra());
	        cierre.setTotal(dto.getCierre().getTotal());
	        cierre.setFechaCierre(LocalDateTime.now());

	        if (Boolean.TRUE.equals(dto.getCierre().getUsoPiezas())) {
	            List<PiezaReparacion> piezas = dto.getCierre().getPiezas().stream().map(p -> {
	                PiezaReparacion pieza = new PiezaReparacion();
	                pieza.setDescripcion(p.getDescripcion());
	                pieza.setCosto(p.getCosto());
	                pieza.setCierreFolio(cierre);
	                return pieza;
	            }).toList();

	            cierre.setPiezas(piezas);
	        }

	        cierreFolioRepository.save(cierre);
	    }

	    // Actualizar estatus del folio
	    folio.setEstatusActual(nuevoEstatus);

	    // NUEVO: Guardar resultado del diagnóstico si viene en el request
	    if (dto.getResultadoDiagnostico() != null && !dto.getResultadoDiagnostico().isEmpty()) {
	        try {
	            folio.setResultadoDiagnostico(ResultadoDiagnostico.valueOf(dto.getResultadoDiagnostico().toUpperCase()));
	            folio.setFechaDiagnostico(LocalDateTime.now());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Resultado de diagnóstico inválido: " + dto.getResultadoDiagnostico());
	        }
	    }

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

	    	   
	    	
	    	    List<HistorialEstatusDTO> comentariosDelEstatusActual = historial.stream()
	    	            .filter(h -> estatusActual.equals(h.getEstatusNuevo()))
	    	            .sorted(Comparator.comparing(
	    	                    HistorialEstatusDTO::getFechaCambio,
	    	                    Comparator.nullsLast(Comparator.naturalOrder())
	    	            ))
	    	            .toList();
	    	    
	    	    
	    	    if (comentariosDelEstatusActual.isEmpty()) {
	    	        HistorialEstatusDTO dummy = new HistorialEstatusDTO();
	    	        dummy.setEstatusAnterior(estatusActual);
	    	        dummy.setEstatusNuevo(estatusActual);
	    	        dummy.setComentario("No hay avances registrados todavía");
	    	        return List.of(dummy);
	    	    }

	    	    return comentariosDelEstatusActual;
	    	
	    }
	 
	    // NUEVO: Método para generar y descargar el PDF del ticket
	    public void generarTicketPdf(Long folioId) {
	        Folio folio = folioRepository.findById(folioId)
	                .orElseThrow(() -> new RuntimeException("Folio no encontrado"));

	        try {
	            // Generar PDF
	            String rutaPdf = ticketPdfService.generateTicketPdf(folio);

	            // Actualizar el folio con la ruta y fecha del ticket
	            folio.setRutaTicket(rutaPdf);
	            folio.setFechaTicket(LocalDateTime.now());
	            folioRepository.save(folio);

	        } catch (Exception e) {
	            throw new RuntimeException("Error al generar ticket PDF: " + e.getMessage(), e);
	        }
	    }

	    
}
