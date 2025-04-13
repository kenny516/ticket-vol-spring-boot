package com.ticketvol.ticketVol.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.ticketvol.ticketVol.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PDFService {
    private final ReservationService reservationService;

    // Définition des couleurs
    private static final BaseColor PRIMARY_COLOR = new BaseColor(0, 123, 255); // Bleu principal
    private static final BaseColor SECONDARY_COLOR = new BaseColor(108, 117, 125); // Gris secondaire
    private static final BaseColor ACCENT_COLOR = new BaseColor(40, 167, 69); // Vert pour les prix

    public byte[] generateReservationPDF(Integer reservationId) throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Ajout d'un en-tête stylisé
            PdfPTable header = new PdfPTable(1);
            header.setWidthPercentage(100);
            header.setSpacingAfter(20);

            // Logo et titre
            Paragraph title = new Paragraph();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
            titleFont.setColor(PRIMARY_COLOR);
            title.add(new Chunk("Billet de Réservation", titleFont));
            title.setAlignment(Element.ALIGN_CENTER);

            PdfPCell headerCell = new PdfPCell(title);
            headerCell.setBorderColor(PRIMARY_COLOR);
            headerCell.setPadding(15);
            headerCell.setBorderWidth(2);
            header.addCell(headerCell);
            document.add(header);

            // Récupération des données de réservation
            Reservation reservation = reservationService.getReservationById(reservationId);

            // Informations principales dans un tableau stylisé
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingAfter(20);

            // Style pour les en-têtes de section
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            sectionFont.setColor(PRIMARY_COLOR);

            // Style pour les valeurs
            Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            valueFont.setColor(SECONDARY_COLOR);

            // Ajout des informations de réservation
            addTableRow(infoTable, "N° de Réservation", String.valueOf(reservation.getId()), sectionFont, valueFont);
            addTableRow(infoTable, "Client",
                    reservation.getUtilisateur().getNom() + " " + reservation.getUtilisateur().getPrenom(), sectionFont,
                    valueFont);

            // Informations du vol
            PdfPTable volTable = new PdfPTable(1);
            volTable.setWidthPercentage(100);
            volTable.setSpacingAfter(20);

            PdfPCell volCell = new PdfPCell();
            volCell.setPadding(10);
            volCell.setBackgroundColor(new BaseColor(240, 240, 240));

            Paragraph volInfo = new Paragraph();
            volInfo.add(new Chunk("DÉTAILS DU VOL\n\n", sectionFont));
            volInfo.add(new Chunk("De: ", sectionFont));
            volInfo.add(new Chunk(reservation.getPlaceVol().getVol().getVilleDepart().getNom() + "\n", valueFont));
            volInfo.add(new Chunk("À: ", sectionFont));
            volInfo.add(new Chunk(reservation.getPlaceVol().getVol().getVilleArrive().getNom() + "\n", valueFont));
            volInfo.add(new Chunk("Date de départ: ", sectionFont));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dateDepart = reservation.getPlaceVol().getVol().getDateDepart().format(formatter);
            volInfo.add(new Chunk(dateDepart + "\n", valueFont));

            volCell.addElement(volInfo);
            volTable.addCell(volCell);
            document.add(volTable);

            // Détails des places
            PdfPTable placeTable = new PdfPTable(2);
            placeTable.setWidthPercentage(100);
            placeTable.setSpacingAfter(20);

            addTableRow(placeTable, "Type de siège", reservation.getPlaceVol().getPlace().getTypeSiege().getDesignation(),
                    sectionFont, valueFont);
            addTableRow(placeTable, "Nombre de places", String.valueOf(reservation.getNbPlaces()), sectionFont,
                    valueFont);
            addTableRow(placeTable, "Adultes", String.valueOf(reservation.getNbAdulte()), sectionFont, valueFont);
            addTableRow(placeTable, "Enfants", String.valueOf(reservation.getNbEnfant()), sectionFont, valueFont);

            document.add(placeTable);

            // Prix total dans un cadre spécial
            PdfPTable prixTable = new PdfPTable(1);
            prixTable.setWidthPercentage(100);

            Font prixFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            prixFont.setColor(ACCENT_COLOR);

            PdfPCell prixCell = new PdfPCell(
                    new Phrase("Prix total: " + String.format("%.2f €", reservation.getPrix()), prixFont));
            prixCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            prixCell.setPadding(10);
            prixCell.setBorderColor(ACCENT_COLOR);
            prixCell.setBorderWidth(2);
            prixTable.addCell(prixCell);

            document.add(prixTable);

            // Pied de page avec mentions légales
            Paragraph footer = new Paragraph();
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8);
            footerFont.setColor(SECONDARY_COLOR);
            footer.add(new Chunk(
                    "\n\nCe billet est personnel et non cessible. Veuillez vous présenter 2 heures avant le départ pour les formalités d'embarquement.",
                    footerFont));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new DocumentException("Erreur lors de la génération du PDF: " + e.getMessage());
        }
    }

    private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorderColor(SECONDARY_COLOR);
        labelCell.setPadding(8);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorderColor(SECONDARY_COLOR);
        valueCell.setPadding(8);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }
}