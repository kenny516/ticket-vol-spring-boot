package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.Utilisateur;
import com.ticketvol.ticketVol.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur login(String pseudo,String motDePasse) {
        return utilisateurRepository.loginUTilisateur(pseudo,motDePasse);
     }
}
