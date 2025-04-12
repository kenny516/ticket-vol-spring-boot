package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationRestController {
    private final PDFService pdfService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getReservationPDF(@PathVariable Integer id) {
        try {
            byte[] pdfContent = pdfService.generateReservationPDF(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(org.springframework.http.ContentDisposition
                    .builder("attachment")
                    .filename("reservation-" + id + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error generating PDF: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}