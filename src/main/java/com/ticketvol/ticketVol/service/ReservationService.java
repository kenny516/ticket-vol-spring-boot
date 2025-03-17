package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.*;
import com.ticketvol.ticketVol.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PromotionService promotionService;

    public List<Reservation> getReservationByIdUtilisateur(Utilisateur utilisateur) {
        return reservationRepository.findByIdUtilisateur(utilisateur);
    }


    public void setPromotion(Reservation reservation){
        PlaceVol placeVol = reservation.getPlaceVol();
        List<Promotion> promotions = promotionService.findPromotionValidByVolAndTypeSiege(placeVol.getVol().getId(),placeVol.getTypeSiege().getId());


        for





    }

}
