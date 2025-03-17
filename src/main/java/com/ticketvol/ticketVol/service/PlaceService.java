package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.Place;
import com.ticketvol.ticketVol.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place findById(int id) {
        return placeRepository.findById(id).orElse(null);
    }


    public Place findPlaceByIdVolAndTypeSiege(int idVol, int idTypeSiege) {
        return placeRepository.findPlaceByVolAndTypeSiege(idVol, idTypeSiege);
    }

    public Boolean

}
