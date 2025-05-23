package com.app.inventoryservice.model;

import com.app.inventoryservice.enums.EtatReclamation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Tache")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sujet;
    @Lob
    private String description;
    private String statut;


    private String userId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        private Reclamation reclamation;

}
