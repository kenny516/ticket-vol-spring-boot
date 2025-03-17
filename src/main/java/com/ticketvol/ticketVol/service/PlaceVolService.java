package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.Place;
import com.ticketvol.ticketVol.model.PlaceVol;
import com.ticketvol.ticketVol.repository.PlaceVolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceVolService {
    private final PlaceVolRepository placeVolRepository;

    public PlaceVol findById(int id) {
        return placeVolRepository.findById(id).orElse(null);
    }

}
