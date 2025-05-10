package com.app.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Factures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private LocalDate dateEmission;
    private Double montant;
    private boolean statut;

    @OneToOne
    private Tache tache;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        private Reclamation reclamation;

}
