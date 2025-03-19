package com.ticketvol.ticketVol.DTO;

import com.ticketvol.ticketVol.model.Avion;
import com.ticketvol.ticketVol.model.Place;
import com.ticketvol.ticketVol.model.Ville;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolDTO {
    Integer idVol;
    LocalDateTime dateDepart;
    Ville villeDepart;
    Ville villeArrivee;
    Avion avion;
    List<Place> placeDispo;
}
