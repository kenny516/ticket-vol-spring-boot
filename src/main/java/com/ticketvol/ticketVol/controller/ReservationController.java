package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.model.PlaceVol;
import com.ticketvol.ticketVol.model.Reservation;
import com.ticketvol.ticketVol.model.Utilisateur;
import com.ticketvol.ticketVol.service.PlaceService;
import com.ticketvol.ticketVol.service.PlaceVolService;
import com.ticketvol.ticketVol.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final PlaceVolService placeVolService;

    @GetMapping("")
    public String getAllReservations(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        List<Reservation> reservations = reservationService.getReservationByIdUtilisateur(utilisateur);
        model.addAttribute("reservations", reservations);
        return "";
    }

    @PostMapping("/reserver")
    public String reserveReservation(HttpSession session,Reservation reservation) {
        Utilisateur Utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        reservation.setUtilisateur(Utilisateur);
        PlaceVol placeVol = placeVolService.findById(reservation.getPlaceVol().getId());
        reservation.setPlaceVol(placeVol);



    }

}
