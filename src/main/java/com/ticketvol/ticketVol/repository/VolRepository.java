package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolRepository extends JpaRepository<Vol, Integer> {


    @Query(value = """
            SELECT *
            FROM vol
            WHERE vol.date_depart > CURRENT_TIMESTAMP
            ORDER BY vol.date_depart
            """, nativeQuery = true)
    List<Vol> findVolValid();
}
