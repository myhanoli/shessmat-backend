package com.hanoli.demojwt.services;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Folio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class TicketPdfService {

    private static final String TICKETS_DIR = "tickets";

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 9);

    // 🔥 Color elegante (azul oscuro profesional)
    private static final BaseColor PRIMARY_COLOR = new BaseColor(40, 60, 90);

    private static final Font COMPANY_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, PRIMARY_COLOR);
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
    private static final Font SECTION_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

    public String generateTicketPdf(Folio folio) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        String path = TICKETS_DIR + "/" + now.getYear() + "/" + String.format("%02d", now.getMonthValue());

        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        String filePath = path + "/" + folio.getFolio() + ".pdf";

        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        addCompanyHeader(document);

        Paragraph titulo = new Paragraph("FORMATO DE RECEPCIÓN DE EQUIPO", TITLE_FONT);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(10);
        titulo.setSpacingAfter(10);
        document.add(titulo);

        addFolioBox(document, folio);

        // ===== CLIENTE =====
        addSection(document, "INFORMACIÓN DEL CLIENTE");
        PdfPTable cliente = createTable();

        if (folio.getCliente() != null) {
            cliente.addCell(createCell("Número:", true));
            cliente.addCell(createCell(folio.getCliente().getNumCliente(), false));

            cliente.addCell(createCell("Nombre:", true));
            cliente.addCell(createCell(folio.getCliente().getNombre(), false));
        }

        document.add(cliente);

        // ===== EQUIPO =====
        addSection(document, "INFORMACIÓN DEL EQUIPO");
        PdfPTable equipo = createTable();

        equipo.addCell(createCell("Tipo:", true));
        equipo.addCell(createCell(folio.getTipoEquipo(), false));

        equipo.addCell(createCell("Marca:", true));
        equipo.addCell(createCell(folio.getMarca(), false));

        equipo.addCell(createCell("Modelo:", true));
        equipo.addCell(createCell(folio.getModelo(), false));

        equipo.addCell(createCell("Serie:", true));
        equipo.addCell(createCell(folio.getNumSerie(), false));

        document.add(equipo);

        // ===== ACCESORIOS =====
        addSection(document, "ACCESORIOS");
        PdfPTable accesorios = createTable();

        accesorios.addCell(createCell("Entrega cargador:", true));
        accesorios.addCell(createCell(
                folio.getTraeCargador() != null && folio.getTraeCargador() ? "Sí" : "No",
                false
        ));

        if (Boolean.TRUE.equals(folio.getTraeCargador())) {
            accesorios.addCell(createCell("Marca cargador:", true));
            accesorios.addCell(createCell(folio.getMarcaCargador(), false));

            accesorios.addCell(createCell("Serie cargador:", true));
            accesorios.addCell(createCell(folio.getNumSerieCargador(), false));
        }

        document.add(accesorios);


        // ===== ENCENDIDO =====
        addSection(document, "ESTADO DEL EQUIPO");
        PdfPTable encendido = createTable();

        encendido.addCell(createCell("Viene Encendido:", true));
        encendido.addCell(createCell(
                folio.getEncendido() != null && folio.getEncendido() ? "Sí" : "No",
                false
        ));

        /*if (Boolean.TRUE.equals(folio.getEncendido())) {
            encendido.addCell(createCell("Marca cargador:", true));
            encendido.addCell(createCell(folio.getMarcaCargador(), false));

            encendido.addCell(createCell("Serie cargador:", true));
            encendido.addCell(createCell(folio.getNumSerieCargador(), false));
        }*/

        document.add(encendido);

        // ===== COMENTARIOS =====
        addSection(document, "DESCRIPCION DE FALLA POR CLIENTE");
        PdfPTable com = new PdfPTable(1);
        com.setWidthPercentage(85);
        com.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell comCell = new PdfPCell(new Phrase(
                folio.getComentarios() != null ? folio.getComentarios() : "Sin descripcion",
                NORMAL_FONT
        ));
        comCell.setPadding(8);
        comCell.setBorderColor(BaseColor.LIGHT_GRAY);

        com.addCell(comCell);
        document.add(com);

        // ===== OBSERVACIONES =====
        addSection(document, "OBSERVACIONES");
        PdfPTable obs = new PdfPTable(1);
        obs.setWidthPercentage(85);
        obs.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell obsCell = new PdfPCell(new Phrase(
                folio.getObservaciones() != null ? folio.getObservaciones() : "Sin observaciones",
                NORMAL_FONT
        ));
        obsCell.setPadding(8);
        obsCell.setBorderColor(BaseColor.LIGHT_GRAY);

        obs.addCell(obsCell);
        document.add(obs);

        document.close();
        return filePath;
    }

    private void addCompanyHeader(Document document) throws Exception {

        try {
            Image logo = Image.getInstance(getClass().getClassLoader().getResource("logoHanoliPc.png"));
            logo.scaleToFit(70, 70);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        } catch (Exception ignored) {}

        Paragraph desc = new Paragraph(
                "Reparación de Computadoras, Celulares y Tablets",
                COMPANY_FONT
        );
        desc.setAlignment(Element.ALIGN_CENTER);
        desc.setSpacingAfter(4);
        document.add(desc);

        Paragraph address = new Paragraph(
                "Laguna Diamante 31, La Cañada",
                SUBTITLE_FONT
        );
        address.setAlignment(Element.ALIGN_CENTER);
        document.add(address);

        // === WhatsApp: icono y número en línea ===
        PdfPTable whatsappTable = new PdfPTable(1);
        whatsappTable.setWidthPercentage(50); // ajusta según necesites
        whatsappTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell whatsappCell = new PdfPCell();
        whatsappCell.setBorder(PdfPCell.NO_BORDER);
        whatsappCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        whatsappCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Phrase whatsappPhrase = new Phrase();

// --- Icono WhatsApp ---
        try {
            Image whatsappIcon = Image.getInstance(getClass().getClassLoader().getResource("whatsapp.png"));
            whatsappIcon.scaleToFit(20, 20); // agrandado para que se vea parejo con otros iconos
            Chunk wsChunk = new Chunk(whatsappIcon, 0, -5); // ajusta vertical para alinearlo con el texto
            whatsappPhrase.add(wsChunk);
            whatsappPhrase.add(new Chunk(" ")); // pequeño espacio entre icono y número
        } catch (Exception ignored) {}

// --- Número de teléfono ---
        whatsappPhrase.add(new Chunk("9626990740", SUBTITLE_FONT));

        whatsappCell.setPhrase(whatsappPhrase);
        whatsappTable.addCell(whatsappCell);

        document.add(whatsappTable);

        // === Redes sociales: todos en una sola celda en línea ===
        PdfPTable socialTable = new PdfPTable(1);
        socialTable.setWidthPercentage(50); // ajusta según necesites
        socialTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell socialCell = new PdfPCell();
        socialCell.setBorder(PdfPCell.NO_BORDER);
        socialCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        socialCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Phrase socialPhrase = new Phrase();

// --- Icono Facebook ---
        try {
            Image fbIcon = Image.getInstance(getClass().getClassLoader().getResource("facebook.png"));
            fbIcon.scaleToFit(16, 16);
            Chunk fbChunk = new Chunk(fbIcon, 0, -3); // -3 ajusta vertical
            socialPhrase.add(fbChunk);
            socialPhrase.add(new Chunk("  ")); // espacio entre iconos
        } catch (Exception ignored) {}

// --- Icono TikTok ---
        try {
            Image ttIcon = Image.getInstance(getClass().getClassLoader().getResource("tiktok.png"));
            ttIcon.scaleToFit(16, 16);
            Chunk ttChunk = new Chunk(ttIcon, 0, -3);
            socialPhrase.add(ttChunk);
            socialPhrase.add(new Chunk("  ")); // espacio antes del texto
        } catch (Exception ignored) {}

// --- Texto @hanolipc ---
        socialPhrase.add(new Chunk("@hanolipc", SUBTITLE_FONT));

        socialCell.setPhrase(socialPhrase);
        socialTable.addCell(socialCell);

        document.add(socialTable);



        // Línea decorativa
        PdfPTable line = new PdfPTable(1);
        line.setWidthPercentage(85);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(1.2f);
        cell.setBorderColorBottom(PRIMARY_COLOR);
        cell.setPadding(5);

        line.addCell(cell);
        document.add(line);
    }

    // ===== FOLIO =====
    private void addFolioBox(Document document, Folio folio) throws Exception {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell f1 = new PdfPCell(new Phrase("Folio: " + folio.getFolio(), HEADER_FONT));
        f1.setBorder(PdfPCell.NO_BORDER);

        PdfPCell f2 = new PdfPCell(new Phrase(
                "Fecha: " + (folio.getFecha() != null ? folio.getFecha() : "N/A"),
                HEADER_FONT
        ));
        f2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        f2.setBorder(PdfPCell.NO_BORDER);

        table.addCell(f1);
        table.addCell(f2);

        PdfPCell wrapper = new PdfPCell(table);
        wrapper.setPadding(8);
        wrapper.setBorderColor(BaseColor.LIGHT_GRAY);

        PdfPTable outer = new PdfPTable(1);
        outer.setWidthPercentage(85);
        outer.setHorizontalAlignment(Element.ALIGN_CENTER);
        outer.addCell(wrapper);

        document.add(new Paragraph(" "));
        document.add(outer);
    }

    // ===== SECTIONS =====
    private void addSection(Document document, String title) throws Exception {

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(85);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell(new Phrase(title, SECTION_FONT));
        cell.setBackgroundColor(PRIMARY_COLOR);
        cell.setPadding(7);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);

        table.addCell(cell);

        document.add(new Paragraph(" "));
        document.add(table);
    }

    // ===== TABLE =====
    private PdfPTable createTable() {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(85);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        return table;
    }

    private PdfPCell createCell(String text, boolean header) {
        PdfPCell cell = new PdfPCell(new Phrase(text != null ? text : "N/A", NORMAL_FONT));
        cell.setPadding(6);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);

        if (header) {
            cell.setBackgroundColor(new BaseColor(245, 245, 245));
        }

        return cell;
    }
}