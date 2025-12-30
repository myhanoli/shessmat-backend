package com.hanoli.demojwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanoli.demojwt.services.EstadisticasService;
import com.hanoli.demojwt.services.FolioService;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class EstadisticasController {
	
	@Autowired
    EstadisticasService estadisticasService;

	
	@GetMapping("/folios/total-por-anio/{anio}")
	public ResponseEntity<?> totalFoliosPorAnio(@PathVariable int anio) {

	    Map<String, Object> response = new HashMap<>();

	    Long total = estadisticasService.obtenerTotalFoliosPorAnio(anio);

	    response.put("anio", anio);
	    response.put("totalFolios", total);

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/folios/por-mes/{anio}")
	public ResponseEntity<?> foliosPorMes(@PathVariable int anio) {

	    return ResponseEntity.ok(
	       estadisticasService.obtenerFoliosPorMes(anio)
	    );
	}
	
	
	@GetMapping("/folios/ranking-clientes")
	public ResponseEntity<?> rankingClientes() {
	    return ResponseEntity.ok(
	    		estadisticasService.obtenerRankingClientes()
	    );
	}
	
	@GetMapping("/folios/ranking-clientes/top10")
	public ResponseEntity<?> top10Clientes() {
	    return ResponseEntity.ok(
	    		estadisticasService.obtenerTop10Clientes()
	    );
	}
	
}
