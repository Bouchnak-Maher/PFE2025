package com.app.inventoryservice.service.Mapper;

import com.app.inventoryservice.Dto.ReclamationDto;
import com.app.inventoryservice.model.Reclamation;

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
                .tacheId(reclamation.getTache() != null ? reclamation.getTache().getId() : null)
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

