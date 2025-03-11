package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Integer> {

    @Query(value = "SELECT * FROM utilisateur " +
            "WHERE pseudo = :pseudo " +
            "AND mot_de_passe = :motDePasse "
            ,nativeQuery = true)
    Utilisateur loginUTilisateur(String pseudo,String motDePasse);
}
