package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "modele", length = 50)
    private String modele;

    @Column(name = "date_fabrication")
    private LocalDate dateFabrication;

    @OneToMany(mappedBy = "idAvion")
    private Set<com.ticketvol.ticketVol.model.Place> places = new LinkedHashSet<>();

}