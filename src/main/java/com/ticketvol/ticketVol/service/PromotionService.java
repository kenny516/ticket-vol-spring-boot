package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.Promotion;
import com.ticketvol.ticketVol.model.TypeSiege;
import com.ticketvol.ticketVol.model.Vol;
import com.ticketvol.ticketVol.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public List<Promotion> findByVol(Vol  vol) {
        return promotionRepository.findByIdVol(vol);
    }

    // encore disponible
    public List<Promotion> findPromotionValidByVol(Vol vol){
        return promotionRepository.findPromotionValideByVol(vol.getId());
    }
    public List<Promotion> findPromotionValidByVolAndTypeSiege(Integer idVol, Integer idTypeSiege){
        return promotionRepository.findPromotionValideByVolAndTypeSiege(idVol, idTypeSiege);
    }

}
