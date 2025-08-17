package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.*;
import com.ticketvol.ticketVol.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PromotionService promotionService;
    private final ParametresService parametresService;

    public List<Reservation> getReservationByIdUtilisateur(Utilisateur utilisateur) {
        List<Reservation> reservations = reservationRepository.findByUtilisateur(utilisateur);

        // Enrichir chaque réservation avec le statut cancelable
        for (Reservation reservation : reservations) {
            reservation.setCancelable(canCancelReservation(reservation));
        }

        return reservations;
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID: " + id));
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.getDateReservation() == null) {
            reservation.setDateReservation(new Date());
        }
        return reservationRepository.save(reservation);
    }

    public void setPromotion(Reservation reservation) {
        PlaceVol placeVol = reservation.getPlaceVol();
        // TODO: Implémenter la logique de promotion si nécessaire
        promotionService.findPromotionValidByVolAndTypeSiege(placeVol.getVol().getId(),
                placeVol.getPlace().getTypeSiege().getId());
    }

    /**
     * Vérifie si une réservation peut être annulée
     */
    public boolean canCancelReservation(Reservation reservation) {
        if (reservation == null || !Boolean.TRUE.equals(reservation.getValider())) {
            return false;
        }

        try {
            // Récupérer le délai d'annulation depuis les paramètres (en heures)
            Parametres delaiParam = parametresService.getParametresByCle("delai_annulation");
            double delaiHeures = delaiParam != null ? Double.parseDouble(delaiParam.getValeur()) : 48.0;

            // Calculer le temps minimum avant le vol pour permettre l'annulation
            LocalDateTime dateVol = reservation.getPlaceVol().getVol().getDateDepart();
            LocalDateTime maintenant = LocalDateTime.now();
            LocalDateTime limiteCancellation = dateVol.minusHours((long) delaiHeures);

            return maintenant.isBefore(limiteCancellation);
        } catch (Exception e) {
            // En cas d'erreur, ne pas permettre l'annulation
            return false;
        }
    }

    /**
     * Annule une réservation
     */
    public boolean cancelReservation(Integer reservationId, Integer userId) {
        try {
            Reservation reservation = getReservationById(reservationId);

            // Vérifier que la réservation appartient à l'utilisateur
            if (!reservation.getUtilisateur().getId().equals(userId)) {
                return false;
            }

            // Vérifier si l'annulation est possible
            if (!canCancelReservation(reservation)) {
                return false;
            }

            // Annuler la réservation
            reservation.setValider(false);
            reservationRepository.save(reservation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
