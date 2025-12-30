package com.hanoli.demojwt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanoli.demojwt.entity.Estatus;
import com.hanoli.demojwt.services.EquipoService;
import com.hanoli.demojwt.services.EstatusService;
import com.hanoli.demojwt.services.MarcaService;
import com.hanoli.shessmat.dto.OpcionDTO;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CatalogosController {
	
	private final MarcaService marcaService;
    private final EquipoService equipoService;
    private final EstatusService estatusService;

    @GetMapping("/marcas")
    public List<OpcionDTO> marcas(@RequestParam String query) {
        return marcaService.buscarPorNombre(query)
                .stream()
                .map(m -> new OpcionDTO(m.getId(), m.getNombre()))
                .toList();
    }

    @GetMapping("/equipos")
    public List<OpcionDTO> equipos(@RequestParam String query) {
        return equipoService.buscarPorNombre(query)
                .stream()
                .map(e -> new OpcionDTO(e.getId(), e.getNombre()))
                .toList();
    }
    
    @GetMapping("/estatus")
    public List<Estatus> getAllEstatus() {
        return estatusService.getAllEstatus();
    }
	

}
