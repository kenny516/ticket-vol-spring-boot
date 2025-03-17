package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nb_siege")
    private Integer nbSiege;

    @Column(name = "pourcentage_reduction")
    private Double pourcentageReduction;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_type_siege", nullable = false)
    private TypeSiege typeSiege;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_vol", nullable = false)
    private Vol vol;

}