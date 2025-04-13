package com.ticketvol.ticketVol.repository;

import com.ticketvol.ticketVol.model.Promotion;
import com.ticketvol.ticketVol.model.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findByVol(Vol vol);

    @Query(value = """
            SELECT promotion.id,
                   promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) as nb_siege,
                   promotion.pourcentage_reduction,
                   promotion.id_type_siege,
                   promotion.id_vol
            FROM promotion
                 JOIN vol v on v.id = promotion.id_vol
                 JOIN public.place p on p.id_type_siege = promotion.id_type_siege AND p.id_avion = v.id_avion
                 LEFT JOIN place_vol pv on p.id = pv.id_place AND pv.id_vol = promotion.id_vol
                 LEFT JOIN public.reservation r on pv.id = r.id_place_vol
            GROUP BY promotion.nb_siege, promotion.id
            HAVING promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) > 0
                OR COALESCE(SUM(r.nb_places), 0) IS NULL
            """, nativeQuery = true)
    List<Promotion> findPromotionValide();

    @Query(value = """
            select promotion.id,
                   promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) as nb_siege,
                   promotion.pourcentage_reduction,
                   promotion.id_type_siege,
                   promotion.id_vol
            from promotion
                 JOIN vol v on v.id = promotion.id_vol
                 JOIN public.place p on p.id_type_siege = promotion.id_type_siege AND p.id_avion = v.id_avion
                 LEFT JOIN place_vol pv on p.id = pv.id_place AND pv.id_vol = promotion.id_vol
                 LEFT JOIN public.reservation r on pv.id = r.id_place_vol
            group by promotion.nb_siege, promotion.id,promotion.id_vol
            having promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) > 0 AND promotion.id_vol = :idVol
                or COALESCE(SUM(r.nb_places), 0) is null;
            """, nativeQuery = true)
    List<Promotion> findPromotionValideByVol(@Param("idVol") Integer idVol);
    @Query(value = """
            select promotion.id,
                   promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) as nb_siege,
                   promotion.pourcentage_reduction,
                   promotion.id_type_siege,
                   promotion.id_vol
            from promotion
                 JOIN vol v on v.id = promotion.id_vol
                 JOIN public.place p on p.id_type_siege = promotion.id_type_siege AND p.id_avion = v.id_avion
                 LEFT JOIN place_vol pv on p.id = pv.id_place AND pv.id_vol = promotion.id_vol
                 LEFT JOIN public.reservation r on pv.id = r.id_place_vol
            group by promotion.nb_siege, promotion.id,promotion.id_vol,promotion.id_type_siege
            having promotion.nb_siege - COALESCE(SUM(r.nb_places), 0) > 0 AND promotion.id_vol = :idVol AND promotion.id_type_siege = :idTypeSiege
                or COALESCE(SUM(r.nb_places), 0) is null;
            """, nativeQuery = true)
    List<Promotion> findPromotionValideByVolAndTypeSiege(@Param("idVol") Integer idVol, @Param("idTypeSiege") Integer idTypeSiege);
}
