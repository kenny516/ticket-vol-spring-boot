package com.ticketvol.ticketVol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_depart")
    private LocalDateTime dateDepart;

    @ManyToOne
    @JoinColumn(name = "id_ville_depart")
    private Ville villeDepart;

    @ManyToOne
    @JoinColumn(name = "id_ville_arrive")
    private Ville villeArrive;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    private Avion avion;
}