package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.PlaceVol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceVolRepository  extends JpaRepository<PlaceVol, Integer> {
}
