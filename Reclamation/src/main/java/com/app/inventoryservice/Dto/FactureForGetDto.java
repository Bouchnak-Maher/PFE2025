package com.app.inventoryservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureForGetDto {
    private  Long id;
    private String reference;
    private LocalDate dateEmission;
    private Double montant;
    private boolean statut;
    private ReclamationForGetDto reclamation; // ID of reclamation
}