package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Reservation;
import com.ticketvol.ticketVol.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUtilisateur(Utilisateur utilisateur);

    @Query("SELECT r FROM Reservation r WHERE r.utilisateur = :utilisateur ORDER BY r.dateReservation DESC")
    List<Reservation> findByUtilisateurOrderByDateReservationDesc(@Param("utilisateur") Utilisateur utilisateur);

    @Query("SELECT r FROM Reservation r WHERE r.utilisateur = :utilisateur AND r.valider = true ORDER BY r.dateReservation DESC")
    List<Reservation> findActiveReservationsByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
}
