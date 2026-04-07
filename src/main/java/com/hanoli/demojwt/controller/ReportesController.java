package com.hanoli.demojwt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanoli.demojwt.services.ReporteService;
import com.hanoli.shessmat.dto.ReporteReparacionDTO;
import com.hanoli.shessmat.dto.ReporteReparacionesResponseDTO;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reparaciones")
    public ResponseEntity<ReporteReparacionesResponseDTO> getReporteReparaciones(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        ReporteReparacionesResponseDTO response = reporteService.getReporteReparaciones(fechaInicio, fechaFin);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reparaciones/excel")
    public ResponseEntity<byte[]> exportarReporteReparacionesExcel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) throws IOException {

        ReporteReparacionesResponseDTO response = reporteService.getReporteReparaciones(fechaInicio, fechaFin);

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Reporte Reparaciones");

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Fecha");
            headerRow.createCell(1).setCellValue("Folio");
            headerRow.createCell(2).setCellValue("Técnico");
            headerRow.createCell(3).setCellValue("Total");
            headerRow.createCell(4).setCellValue("Mano de Obra");
            headerRow.createCell(5).setCellValue("Uso Piezas");

            // Llenar datos
            int rowNum = 1;
            for (ReporteReparacionDTO dto : response.getData()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dto.getFecha() != null ? dto.getFecha().toString() : "");
                row.createCell(1).setCellValue(dto.getFolio() != null ? dto.getFolio() : "");
                row.createCell(2).setCellValue(dto.getTecnico() != null ? dto.getTecnico() : "");
                row.createCell(3).setCellValue(dto.getTotal() != null ? dto.getTotal().doubleValue() : 0.0);
                row.createCell(4).setCellValue(dto.getManoObra() != null ? dto.getManoObra().doubleValue() : 0.0);
                row.createCell(5).setCellValue(dto.getUsoPiezas() != null ? dto.getUsoPiezas().toString() : "");
            }

            // Auto ajustar columnas
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir a byte array
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "reporte_reparaciones.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        }
    }
}
