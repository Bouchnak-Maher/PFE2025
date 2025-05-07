package com.app.inventoryservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TacheDto {
    private Long id;
    private String sujet;
    private String description;
    private String statut;
    private String userId;
    private Long reclamationId; // Just using the ID to avoid recursive JSON
}
