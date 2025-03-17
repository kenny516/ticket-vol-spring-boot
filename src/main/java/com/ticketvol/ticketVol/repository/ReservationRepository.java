package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Reservation;
import com.ticketvol.ticketVol.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByIdUtilisateur(Utilisateur idUtilisateur);
}
