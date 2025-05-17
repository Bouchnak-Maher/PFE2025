package com.app.inventoryservice.service;

import com.app.inventoryservice.Dto.ReclamationDto;
import com.app.inventoryservice.enums.EtatReclamation;
import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.repository.ReclamationRepository;
import com.app.inventoryservice.service.Mapper.ReclamationMapper;
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
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    public ReclamationDto createReclamation(Reclamation r, String username) {
        r.setCreatedBy(username);
        r.setDateCreation(LocalDateTime.now());
        // Save the reclamation entity to the repository
        Reclamation savedReclamation = reclamationRepository.save(r);

        return ReclamationMapper.toDto(savedReclamation);
    }
    @Transactional
    public List<ReclamationDto> getUserReclamations(String username) {
        List<Reclamation> reclamations = reclamationRepository.findByCreatedBy(username);

        // Map the list of Reclamation entities to ReclamationDto
        return reclamations.stream()
                .map(ReclamationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReclamationDto> getAllReclamations() {
        List<Reclamation> reclamations = reclamationRepository.findAll();

        // Map the list of Reclamation entities to ReclamationDto
        return reclamations.stream()
                .map(ReclamationMapper::toDto)
                .collect(Collectors.toList());
    }


    public Optional<Reclamation> getReclamation(Long id) {
        return reclamationRepository.findById(id);
    }

    public void deleteReclamation(Long id) {
        Reclamation r = reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));

        if (r.getStatus() != EtatReclamation.ENVOYE)
            throw new RuntimeException("Impossible de supprimer cette réclamation");
        reclamationRepository.deleteById(id);
    }

    public Reclamation updateEtat(Long id, EtatReclamation nouvelEtat) {
        Reclamation r = reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        r.setStatus(nouvelEtat);
        return reclamationRepository.save(r);
    }
    public Reclamation updateReclamation(Long id, Reclamation updated, String username) {
        Reclamation existing = reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));

        // Optional: Check if `username` matches creator
        if (!existing.getCreatedBy().equals(username)) {
            throw new RuntimeException("Vous n'êtes pas autorisé à modifier cette réclamation.");
        }

        existing.setSujet(updated.getSujet());
        existing.setDescription(updated.getDescription());
        existing.setDateCreation(updated.getDateCreation());
        existing.setType(updated.getType());

        return reclamationRepository.save(existing);
    }

    public long countEmployeesWithReclamations() {
        return reclamationRepository.countDistinctEmployeesWithReclamations();
    }

}

