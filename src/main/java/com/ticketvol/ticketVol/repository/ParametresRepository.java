package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Parametres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametresRepository extends JpaRepository<Parametres, Integer> {

    Parametres findParametresByCle(String cle);
}
