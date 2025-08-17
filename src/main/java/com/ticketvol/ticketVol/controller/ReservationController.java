package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.model.Place;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final PlaceVolService placeVolService;
    private final PlaceService placeService;

    @GetMapping("")
    public String getAllReservations(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        if (utilisateur == null) {
            return "redirect:/login";
        }

        // Vérifier si l'utilisateur est un client (les admins ne peuvent pas voir les
        // réservations)
        if (!"client".equals(utilisateur.getRole())) {
            // Rediriger les non-clients vers la page d'accueil avec un message d'erreur
            return "redirect:/?error=access-denied";
        }

        List<Reservation> reservations = reservationService.getReservationByIdUtilisateur(utilisateur);
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    @PostMapping("/reserver")
    public String reserveReservation(HttpSession session, BindingResult result, Reservation reservation) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        reservation.setUtilisateur(utilisateur);
        PlaceVol placeVol = placeVolService.findById(reservation.getPlaceVol().getId());
        reservation.setPlaceVol(placeVol);

        if (placeService.placeIsAvailable(reservation.getPlaceVol().getVol().getId(),
                reservation.getPlaceVol().getPlace().getTypeSiege().getId(), reservation.getNbPlaces())) {
            // Sauvegarde de la réservation avec la date automatiquement initialisée
            reservationService.saveReservation(reservation);
            return "redirect:/reservation";
        } else {
            Place placeDispo = placeService.findPlaceByIdVolAndTypeSiege(reservation.getPlaceVol().getVol().getId(),
                    reservation.getPlaceVol().getPlace().getTypeSiege().getId());
            result.rejectValue("nbPlace", "error.nbPlace",
                    "Nombre de place insuffisant pour ce vol place restant " + placeDispo.getNombre());
            return "/reservation/formulaire";
        }
    }

    @PostMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        if (utilisateur == null) {
            return "redirect:/login";
        }

        boolean success = reservationService.cancelReservation(id, utilisateur.getId());

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Réservation annulée avec succès.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Impossible d'annuler la réservation. Vérifiez les conditions d'annulation.");
        }

        return "redirect:/reservation";
    }
}
