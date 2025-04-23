package com.app.inventoryservice.controller;

import com.app.inventoryservice.request.MedicamentRequest;
import com.app.inventoryservice.response.MedicamentResponse;
import com.app.inventoryservice.service.MedicamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/medicaments")
@RequiredArgsConstructor
public class MedicamentController {

    private final MedicamentService service;

    @PostMapping("/createMed")
    public ResponseEntity<Integer> createMed(
            @RequestBody @Valid MedicamentRequest request
    ) {
        return ResponseEntity.ok(this.service.createMedicament(request));
    }

    @PutMapping("/updateMed")
    public ResponseEntity<Void> updateMed(
            @RequestBody @Valid MedicamentRequest request
    ) {
        this.service.updateMedicament(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MedicamentResponse>> findAll() {
        return ResponseEntity.ok(this.service.findAllMedicaments());
    }

    @GetMapping("/exists/{medicament-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("medicament-id") Integer medId
    ) {
        return ResponseEntity.ok(this.service.existsById(medId));
    }

    @GetMapping("/find/{medicament-id}")
    public ResponseEntity<MedicamentResponse> findById(
            @PathVariable("medicament-id") Integer medId
    ) {
        return ResponseEntity.ok(this.service.findById(medId));
    }

    @DeleteMapping("/delete/{medicament-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("medicament-id") Integer medId
    ) {
        this.service.deleteMedicament(medId);
        return ResponseEntity.accepted().build();
    }
}
