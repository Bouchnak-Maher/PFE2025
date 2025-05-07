package com.app.inventoryservice.controller;

import com.app.inventoryservice.Dto.TacheDto;
import com.app.inventoryservice.enums.EtatReclamation;
import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.model.Tache;
import com.app.inventoryservice.service.ReclamationService;
import com.app.inventoryservice.service.TacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tache")
@RequiredArgsConstructor
@CrossOrigin("*")

public class TacheController {
    @Autowired private TacheService service;

    @PostMapping
    public ResponseEntity<Tache> create(@RequestBody TacheDto t) {
        return ResponseEntity.ok(service.create(t));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<?> assign(@PathVariable Long id, @RequestParam String userId) {
        service.assignToUser(id, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<?> updateStatut(@PathVariable Long id, @RequestParam String statut) {
        service.updateStatut(id, statut);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/{userId}")
    public List<TacheDto> getMyTaches(@PathVariable String userId) {
        return service.getByAssignee(userId);
    }
    @GetMapping
    public ResponseEntity<List<TacheDto>> getAllTaches() {
        List<Tache> taches = service.getAllTaches();
        List<TacheDto> tacheDtos = taches.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tacheDtos);
    }
    private TacheDto convertToDto(Tache tache) {
        return TacheDto.builder()
                .id(tache.getId())
                .sujet(tache.getSujet())
                .description(tache.getDescription())
                .statut(tache.getStatut())
                .userId(tache.getUserId())
                .reclamationId(tache.getReclamation().getId())  // Assuming you want to include Reclamation ID in the DTO
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    @PutMapping("/{id}")
    public ResponseEntity<TacheDto> updateTache(@PathVariable Long id, @RequestBody Tache updatedTache) {
        TacheDto tache = service.updateTache(id, updatedTache);
        return ResponseEntity.ok(tache);
    }

}
