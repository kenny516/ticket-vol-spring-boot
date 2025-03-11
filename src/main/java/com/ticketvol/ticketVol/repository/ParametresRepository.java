package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Parametres;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ParametresRepository extends JpaRepository<Parametres, Integer> {

    Parametres findParametresByCle(String cle);
}
