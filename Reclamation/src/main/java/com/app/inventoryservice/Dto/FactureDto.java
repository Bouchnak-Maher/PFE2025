package com.app.inventoryservice.Dto;

import com.app.inventoryservice.enums.EtatReclamation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureDto {
    private  Long id;
    private String reference;
    private LocalDate dateEmission;
    private Double montant;
    private boolean statut;
    private Long reclamation; // ID of reclamation
}