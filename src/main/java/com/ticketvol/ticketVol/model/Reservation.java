package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(name = "date_reservation", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateReservation;

    private Integer nbPlaces;
    private Integer nbAdulte;
    private Integer nbEnfant;

    @Column(name = "promotion_detail")
    private String promotionDetail;

    @ManyToOne
    @JoinColumn(name = "id_place_vol")
    private PlaceVol placeVol;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @Transient
    private Boolean isCancelable;

    public Boolean getCancelable() {
        return isCancelable;
    }

    public void setCancelable(Boolean cancelable) {
        isCancelable = cancelable;
    }
}