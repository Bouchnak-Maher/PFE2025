package com.app.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "StockMedicament")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Medicament medicament;
    private int quantiteDisponible;
}
