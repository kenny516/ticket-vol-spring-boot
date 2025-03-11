package com.ticketvol.ticketVol.service;


import com.ticketvol.ticketVol.model.Parametres;
import com.ticketvol.ticketVol.repository.ParametresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParametresService {
    private final ParametresRepository parametresRepository;

    public Parametres createParametres(Parametres parametres) {
        return parametresRepository.save(parametres);
    }

    public List<Parametres> getAllParametres() {
        return parametresRepository.findAll();
    }

    public Parametres getParametresByCle(String cle){
        return parametresRepository.findParametresByCle(cle);
    }

    public Parametres updateParametres(Parametres parametres) {
         return parametresRepository.save(parametres);
    }
}
