package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {


    @Query(value = """
               SELECT
                       place.id,
                       place.nombre - COALESCE(SUM(r.nb_places),0) as nombre,
                       place.
               id_type_siege,
                       place.id_avion
                   FROM place
                        JOIN public.avion a on a.id = place.id_avion
                        JOIN public.vol v on a.id = v.id_avion
                        JOIN public.place_vol pv on pv.id_place = place.id AND pv.id_vol = v.id
                        LEFT JOIN public.reservation r on r.id_place_vol = pv.id
                        WHERE v.id = :idVol AND place.id_type_siege = :idTypeSiege
                   group by  place.id;
            """,nativeQuery = true)
    Place findPlaceByVolAndTypeSiege(@Param("idVol") Integer idVol,@Param("idTypeSiege") Integer idTypeSiege);

}
