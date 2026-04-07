package com.hanoli.demojwt.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.CierreFolio;
import com.hanoli.demojwt.repository.CierreFolioRepository;
import com.hanoli.shessmat.dto.ReporteReparacionDTO;
import com.hanoli.shessmat.dto.ReporteReparacionesResponseDTO;

@Service
public class ReporteService {

    @Autowired
    private CierreFolioRepository cierreFolioRepository;

    public ReporteReparacionesResponseDTO getReporteReparaciones(LocalDate fechaInicio, LocalDate fechaFin) {
        // Convertir LocalDate a LocalDateTime
        LocalDateTime startDate = fechaInicio.atStartOfDay();
        LocalDateTime endDate = fechaFin.atTime(LocalTime.MAX); // Hasta el final del día

        // Obtener los cierres en el rango
        List<CierreFolio> cierres = cierreFolioRepository.findByFechaCierreBetween(startDate, endDate);

        // Convertir a DTOs
        List<ReporteReparacionDTO> data = cierres.stream().map(this::convertToDTO).collect(Collectors.toList());

        // Calcular totales
        int totalReparaciones = cierres.size();
        BigDecimal totalIngresos = cierres.stream()
                .map(CierreFolio::getTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalManoObra = cierres.stream()
                .map(CierreFolio::getManoObra)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ReporteReparacionesResponseDTO(totalReparaciones, totalIngresos, totalManoObra, data);
    }

    private ReporteReparacionDTO convertToDTO(CierreFolio cierre) {
        String tecnico = null;
        if (cierre.getHistorialEstatus() != null && cierre.getHistorialEstatus().getUsuario() != null) {
            tecnico = cierre.getHistorialEstatus().getUsuario().getNombre() + " " +
                      cierre.getHistorialEstatus().getUsuario().getApellidoPat() + " " +
                      cierre.getHistorialEstatus().getUsuario().getApellidoMat();
        }

        return new ReporteReparacionDTO(
                cierre.getFechaCierre(),
                cierre.getFolio().getFolio(),
                tecnico,
                cierre.getTotal(),
                cierre.getManoObra(),
                cierre.getUsoPiezas()
        );
    }
}
