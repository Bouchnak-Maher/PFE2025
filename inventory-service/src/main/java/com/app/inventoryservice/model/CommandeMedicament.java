package com.app.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "CommandeMedicament")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Medicament medicament;
    private int quantiteCommandee;
    private LocalDate dateCommande;
    private LocalDate dateLivraison;
}
