package com.ticketvol.ticketVol.service;

import com.ticketvol.ticketVol.model.Avion;
import com.ticketvol.ticketVol.repository.AvionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvionService {
    private final AvionRepository avionRepository;

    public List<Avion> findAll() {
        return avionRepository.findAll();
    }

    public Avion findById(Integer id) {
        return avionRepository.findById(id).orElse(null);
    }



}
