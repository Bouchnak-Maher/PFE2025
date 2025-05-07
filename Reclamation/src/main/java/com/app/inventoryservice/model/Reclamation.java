package com.app.inventoryservice.model;

import com.app.inventoryservice.enums.EtatReclamation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Reclamation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sujet;
    private String description;

    @Enumerated(EnumType.STRING)
    private EtatReclamation status = EtatReclamation.ENVOYE;

    private String type; // INTERNE ou EXTERNE

    private String createdBy; // username ou email du créateur

    private LocalDateTime dateCreation;

    @OneToOne(mappedBy = "reclamation", cascade = CascadeType.ALL)
    private Tache tache;
}
