package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.DTO.VolDTO;
import com.ticketvol.ticketVol.model.Place;
import com.ticketvol.ticketVol.model.Vol;
import com.ticketvol.ticketVol.repository.PlaceRepository;
import com.ticketvol.ticketVol.repository.VolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VolService {
    private final VolRepository volRepository;
    private final PlaceService placeService;

    public Vol findById(int id) {
        return volRepository.findById(id).orElse(null);
    }

    public List<Vol> findAllVolValid() {
        return volRepository.findVolValid();
    }

    ///  DTO

    public List<VolDTO> findAllVolDTOValid() {
        List<Vol> vols = volRepository.findVolValid();
        List<VolDTO> volDTOs = new ArrayList<>();
        for (Vol vol : vols) {
            VolDTO dto = new VolDTO();
            dto.setIdVol(vol.getId());
            dto.setDateDepart(vol.getDateDepart());
            dto.setVilleDepart(vol.getVilleDepart());
            dto.setVilleArrivee(vol.getVilleArrive());
            dto.setAvion(vol.getAvion());
            List<Place> placesDispo = new ArrayList<>();
            List<Place> places = placeService.findByAvion(vol.getAvion());
            for (Place place : places) {
                placesDispo.add(placeService.findPlaceByIdVolAndTypeSiege(vol.getId(), place.getTypeSiege().getId()));
            }
            dto.setPlaceDispo(placesDispo);
            volDTOs.add(dto);
        }
        return volDTOs;
    }

    public VolDTO findVolDTOById(int id) {
        Vol vol = volRepository.findById(id).orElse(null);
        if (vol == null) {
            return null;
        }
        VolDTO dto = new VolDTO();
        dto.setIdVol(vol.getId());
        dto.setDateDepart(vol.getDateDepart());
        dto.setVilleDepart(vol.getVilleDepart());
        dto.setVilleArrivee(vol.getVilleArrive());
        dto.setAvion(vol.getAvion());
        List<Place> placesDispo = new ArrayList<>();
        List<Place> places = placeService.findByAvion(vol.getAvion());
        for (Place place : places) {
            placesDispo.add(placeService.findPlaceByIdVolAndTypeSiege(vol.getId(), place.getTypeSiege().getId()));
        }
        dto.setPlaceDispo(placesDispo);
        return dto;
    }
}
