package com.app.inventoryservice.service.Mapper;

import com.app.inventoryservice.Dto.ReclamationDto;
import com.app.inventoryservice.Dto.ReclamationForGetDto;
import com.app.inventoryservice.Dto.TacheDto;
import com.app.inventoryservice.model.Reclamation;
import org.springframework.stereotype.Component;

@Component
public class ReclamationMapper {

    public static ReclamationDto toDto(Reclamation reclamation) {
        if (reclamation == null) return null;

        return ReclamationDto.builder()
                .id(reclamation.getId())
                .sujet(reclamation.getSujet())
                .description(reclamation.getDescription())
                .status(reclamation.getStatus())
                .type(reclamation.getType())
                .createdBy(reclamation.getCreatedBy())
                .dateCreation(reclamation.getDateCreation())
                .factureId(reclamation.getFacture() != null ? reclamation.getFacture().getId() : null)
                .tacheId(reclamation.getTache() != null ? reclamation.getTache().getId() : null)
                .build();
    }

    public static ReclamationForGetDto toDtoForGet(Reclamation reclamation) {
        if (reclamation == null) return null;

        // Map the Tache to TacheDto
        TacheDto tacheDto = reclamation.getTache() != null ? TacheDto.builder()
                .id(reclamation.getTache().getId())
                .sujet(reclamation.getTache().getSujet())
                .description(reclamation.getTache().getDescription())
                .statut(reclamation.getTache().getStatut())
                .userId(reclamation.getTache().getUserId())
                .reclamationId(reclamation.getTache().getReclamation() != null ? reclamation.getTache().getReclamation().getId() : null)
                .build() : null;
        return ReclamationForGetDto.builder()
                .id(reclamation.getId())
                .sujet(reclamation.getSujet())
                .description(reclamation.getDescription())
                .status(reclamation.getStatus())
                .type(reclamation.getType())
                .createdBy(reclamation.getCreatedBy())
                .dateCreation(reclamation.getDateCreation())
                .factureId(reclamation.getFacture() != null ? reclamation.getFacture().getId() : null)
                .tache(tacheDto)
                .build();
    }
    public static Reclamation toEntity(ReclamationDto dto) {
        if (dto == null) return null;

        return Reclamation.builder()
                .id(dto.getId())
                .sujet(dto.getSujet())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .type(dto.getType())
                .createdBy(dto.getCreatedBy())
                .dateCreation(dto.getDateCreation())
                .build();
    }
}

