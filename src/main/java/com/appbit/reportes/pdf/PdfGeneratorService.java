package com.appbit.reportes.pdf;

import com.appbit.reportes.entity.ReporteEsg;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {

    public byte[] generar(ReporteEsg reporte) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Reporte ESG — " + reporte.getEmpresaNombre())
                    .setFontSize(18)
                    .setBold());

            document.add(new Paragraph("Generado: " +
                    reporte.getGeneradoEn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .setFontSize(10)
                    .setFontColor(ColorConstants.GRAY));

            document.add(new Paragraph("\n"));

            Table tabla = new Table(UnitValue.createPercentArray(new float[]{1, 1}))
                    .useAllAvailableWidth();

            agregarFila(tabla, "Total candidatos analizados", String.valueOf(reporte.getTotalAnalizados()));
            agregarFila(tabla, "Candidatos con evidencia de diversidad", String.valueOf(reporte.getTotalConBadge()));
            agregarFila(tabla, "Porcentaje de diversidad", String.format("%.1f%%", reporte.getPorcentajeDiversidad()));
            agregarFila(tabla, "Meta de diversidad", String.format("%.1f%%", reporte.getMetaDiversidad()));
            agregarFila(tabla, "Estado de la meta", reporte.isMetaCumplida() ? "Cumplida" : "No cumplida");
            agregarFila(tabla, "Regiones analizadas", reporte.getRegionesAnalizadas());

            document.add(tabla);

            document.add(new Paragraph("\nResumen ESG").setFontSize(14).setBold());
            document.add(new Paragraph(reporte.getDiversidadResultado())
                    .setTextAlignment(TextAlignment.JUSTIFIED));

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el reporte "+e.getMessage());
        }

        return baos.toByteArray();
    }

    private void agregarFila(Table tabla, String etiqueta, String valor) {
        tabla.addCell(new Paragraph(etiqueta).setBold());
        tabla.addCell(new Paragraph(valor != null ? valor : "N/A"));
    }
}