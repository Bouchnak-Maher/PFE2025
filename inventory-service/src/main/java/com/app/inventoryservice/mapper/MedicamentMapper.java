package com.app.inventoryservice.mapper;

import com.app.inventoryservice.model.Medicament;
import org.springframework.stereotype.Component;
import com.app.inventoryservice.request.MedicamentRequest;
import com.app.inventoryservice.response.MedicamentResponse;

@Component
public class MedicamentMapper {
    public Medicament toMedicament(MedicamentRequest request) {
        if (request == null) {
            return null;
        }
        return Medicament.builder()
                .id(request.id())
                .nom(request.nom())
                .description(request.description())
                .prix(request.prix())
                .quantiteEnStock(request.quantiteEnStock())
                .dateExpiration(request.dateExpiration())
                .build();
    }

    public MedicamentResponse fromMedicament(Medicament medicament) {
        if (medicament == null) {
            return null;
        }
        return new MedicamentResponse(
                medicament.getId(),
                medicament.getNom(),
                medicament.getDescription(),
                medicament.getPrix(),
                medicament.getQuantiteEnStock(),
                medicament.getDateExpiration()
        );
    }
}
