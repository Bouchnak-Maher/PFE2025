package com.app.inventoryservice.Dto;

import com.app.inventoryservice.enums.EtatReclamation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReclamationForGetDto {
    private Long id;
    private String sujet;
    private String description;
    private EtatReclamation status;
    private String type;
    private String createdBy;
    private LocalDateTime dateCreation;
    private TacheDto tache;
    private Long factureId;
}