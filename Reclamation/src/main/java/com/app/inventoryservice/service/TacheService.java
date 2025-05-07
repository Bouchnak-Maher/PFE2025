package com.app.inventoryservice.service;

import com.app.inventoryservice.Dto.TacheDto;
import com.app.inventoryservice.enums.EtatReclamation;
import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.model.Tache;
import com.app.inventoryservice.repository.ReclamationRepository;
import com.app.inventoryservice.repository.TacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    private final ReclamationRepository reclamationRepository;

    public Tache create(TacheDto tacheDto) {
        if (tacheDto.getReclamationId() == null) {
            throw new IllegalArgumentException("Réclamation requise pour créer une tâche.");
        }

        // Find the Reclamation associated with the Tache
        Reclamation reclamation = reclamationRepository.findById(tacheDto.getReclamationId())
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));

        // Check if a Tache already exists for this Reclamation
        if (reclamation.getTache() != null) {
            throw new IllegalArgumentException("Cette réclamation a déjà une tâche.");
        }

        // Set default statut for the new Tache
        tacheDto.setStatut("Envoyée");

        // Map the DTO to the Tache entity
        Tache tache = Tache.builder()
                .sujet(tacheDto.getSujet())
                .description(tacheDto.getDescription())
                .statut(tacheDto.getStatut())
                .userId(tacheDto.getUserId())
                .reclamation(reclamation)
                .build();

        // Save the new Tache, the bidirectional relation is automatically handled by JPA
        return tacheRepository.save(tache);
    }




    public void assignToUser(Long tacheId, String assigneeId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        if (!"Envoyée".equalsIgnoreCase(tache.getStatut())) {
            throw new IllegalStateException("Seules les tâches 'Envoyée' peuvent être assignées.");
        }

        tache.setUserId(assigneeId); // on stocke l'ID ou le username
        tache.setStatut("Assignée");

        tacheRepository.save(tache);
    }

    public void updateStatut(Long id, String statut) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        if ("Terminée".equalsIgnoreCase(statut) && tache.getUserId() == null) {
            throw new IllegalStateException("La tâche doit être assignée avant d'être terminée.");
        }

        tache.setStatut(statut);
        tacheRepository.save(tache);
    }

    public void deleteIfEnvoyee(Long id) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        if (!"Envoyée".equalsIgnoreCase(tache.getStatut())) {
            throw new IllegalStateException("Seules les tâches 'Envoyée' peuvent être supprimées.");
        }

        tacheRepository.deleteById(id);
    }
    public List<TacheDto> getByAssignee(String assigneeId) {
        // Fetch all Tache entities for the given assigneeId
        List<Tache> taches = tacheRepository.findByUserId(assigneeId);

        // Convert each Tache entity to TacheDto
        return taches.stream()
                .map(this::convertToDto)  // Convert each Tache entity to a DTO
                .collect(Collectors.toList());
    }

    // Convert Tache entity to TacheDto
    private TacheDto convertToDto(Tache tache) {
        return TacheDto.builder()
                .id(tache.getId())
                .sujet(tache.getSujet())
                .description(tache.getDescription())
                .statut(tache.getStatut())
                .userId(tache.getUserId())
                .reclamationId(tache.getReclamation().getId())  // Assuming you want to include Reclamation ID
                .build();
    }

    public List<Tache> getAll() {
        return tacheRepository.findAll();
    }
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();  // Fetch all Tache entities from the repository
    }
    @Transactional
    public void delete(Long id) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache not found"));

        // Unlink the associated Reclamation
        tache.setReclamation(null);
        tacheRepository.save(tache); // Save to persist the null

        // Now delete the Tache
        tacheRepository.deleteById(id);
    }

    @Transactional
    public TacheDto updateTache(Long id, Tache updatedTache) {
        Tache existingTache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable"));

        // Update only allowed fields (excluding statut)
        existingTache.setSujet(updatedTache.getSujet());
        existingTache.setDescription(updatedTache.getDescription());
        existingTache.setUserId(updatedTache.getUserId());

        // Save and convert to DTO
        Tache savedTache = tacheRepository.save(existingTache);
        return this.convertToDto(savedTache);
    }


}
