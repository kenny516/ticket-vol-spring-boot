package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {
}
