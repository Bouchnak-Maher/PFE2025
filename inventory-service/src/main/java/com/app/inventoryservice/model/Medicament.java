package com.app.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Medicament")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    private Double prix;
    private Integer quantiteEnStock;
    private LocalDate dateExpiration;
}
