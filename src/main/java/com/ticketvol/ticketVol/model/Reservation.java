package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double prix;
    private Boolean valider = true;
    private Integer nbPlaces;
    private Integer nbAdulte;
    private Integer nbEnfant;

    @ManyToOne
    @JoinColumn(name = "id_place_vol")
    private PlaceVol placeVol;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}