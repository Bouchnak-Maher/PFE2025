package com.app.inventoryservice.controller;

import com.app.inventoryservice.Dto.FactureDto;
import com.app.inventoryservice.Dto.FactureForGetDto;
import com.app.inventoryservice.Dto.TacheDto;
import com.app.inventoryservice.model.Facture;
import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.model.Tache;
import com.app.inventoryservice.repository.FactureRepository;
import com.app.inventoryservice.repository.ReclamationRepository;
import com.app.inventoryservice.service.Mapper.ReclamationMapper;
import com.app.inventoryservice.service.TacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
@CrossOrigin("*")

public class FactureController {
    @Autowired  private FactureRepository factureRepository;
    @Autowired  private ReclamationRepository reclamationRepository;
    @Autowired  private ReclamationMapper reclamationMapper;


    @PostMapping
    public ResponseEntity<FactureDto> addFacture(@RequestBody FactureDto request) {
        Reclamation reclamation = reclamationRepository.findById(request.getReclamation())
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));

        Facture facture = Facture.builder()
                .reference(request.getReference())
                .dateEmission(request.getDateEmission())
                .montant(request.getMontant())
                .statut(request.isStatut())
                .reclamation(reclamation)
                .build();

        // Set the reverse relation
        reclamation.setFacture(facture);

        // Save both entities in the correct order
        factureRepository.save(facture); // cascade will save the Reclamation update too

        // Optionally: update the dto with the saved id
        FactureDto response = FactureDto.builder()
                .id(facture.getId())
                .reference(facture.getReference())
                .dateEmission(facture.getDateEmission())
                .montant(facture.getMontant())
                .statut(facture.isStatut())
                .reclamation(reclamation.getId())
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public List<FactureForGetDto> getAllFactures(@RequestParam(required = false) String userId) {
        List<Facture> factures;

        if (userId != null) {
            // Fetch factures where the tache's userId matches the provided userId
            factures = factureRepository.findByReclamationTacheUserId(userId);
        } else {
            // Fetch all factures
            factures = factureRepository.findAll();
        }

        return factures.stream()
                .map(facture -> FactureForGetDto.builder()
                        .id(facture.getId())
                        .reference(facture.getReference())
                        .dateEmission(facture.getDateEmission())
                        .montant(facture.getMontant())
                        .statut(facture.isStatut())
                        .reclamation(reclamationMapper.toDtoForGet(facture.getReclamation()))
                        .build())
                .collect(Collectors.toList());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable Long id) {
        return factureRepository.findById(id).map(facture -> {
            // Optionally unlink the facture from the associated reclamation
            Reclamation reclamation = facture.getReclamation();
            if (reclamation != null) {
                reclamation.setFacture(null);
                reclamationRepository.save(reclamation);
            }

            factureRepository.delete(facture);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFactureStatut(
            @PathVariable Long id,
            @RequestBody Boolean newStatut) {
        // Find the facture by ID
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture not found"));

        // Update the statut
        facture.setStatut(newStatut);

        // Save the updated facture
        Facture updatedFacture = factureRepository.save(facture);

        // Return the updated facture as a DTO
        FactureDto response = FactureDto.builder()
                .id(updatedFacture.getId())
                .reference(updatedFacture.getReference())
                .dateEmission(updatedFacture.getDateEmission())
                .montant(updatedFacture.getMontant())
                .statut(updatedFacture.isStatut())
                .reclamation(updatedFacture.getReclamation() != null ? updatedFacture.getReclamation().getId() : null)
                .build();

        return ResponseEntity.ok(response);
    }

}
