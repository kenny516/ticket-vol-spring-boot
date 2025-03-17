package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prix")
    private Double prix;

    @ColumnDefault("true")
    @Column(name = "valider")
    private Boolean valider;

    @Column(name = "nb_places")
    private Integer nbPlaces;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_place_vol", nullable = false)
    private PlaceVol placeVol;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "nb_adulte")
    private Integer nbAdulte;

    @Column(name = "nb_enfant")
    private Integer nbEnfant;

    @Column(name = "id_promotion")
    private Integer idPromotion;

}