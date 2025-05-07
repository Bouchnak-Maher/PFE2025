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
public class ReclamationDto {
    private Long id;
    private String sujet;
    private String description;
    private EtatReclamation status;
    private String type;
    private String createdBy;
    private LocalDateTime dateCreation;
    private Long tacheId;  // Just the ID of the associated Tache to avoid recursion
}