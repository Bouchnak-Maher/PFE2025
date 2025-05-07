package com.app.inventoryservice.controller;

import com.app.inventoryservice.Dto.ReclamationDto;
import com.app.inventoryservice.enums.EtatReclamation;
import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.service.ReclamationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping
    public ResponseEntity<ReclamationDto> create(@RequestBody Reclamation r,
                                                 @RequestParam String username) {
        return ResponseEntity.ok(reclamationService.createReclamation(r, username));
    }

    @GetMapping("/me")
    public ResponseEntity<List<ReclamationDto>> getMyReclamations(@RequestParam String username) {
        return ResponseEntity.ok(reclamationService.getUserReclamations(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReclamationDto>> getAll() {
        return ResponseEntity.ok(reclamationService.getAllReclamations());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam String username) {
        reclamationService.deleteReclamation(id, username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> updateEtat(@PathVariable Long id,
                                                  @RequestParam EtatReclamation etat) {
        return ResponseEntity.ok(reclamationService.updateEtat(id, etat));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Reclamation> updateReclamation(
            @PathVariable long id,
            @RequestBody Reclamation updated,
            @RequestParam String username) {
        return ResponseEntity.ok(reclamationService.updateReclamation(id, updated, username));
    }

}
