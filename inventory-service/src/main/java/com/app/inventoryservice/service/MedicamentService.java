package com.app.inventoryservice.service;

import com.app.inventoryservice.model.Medicament;
import com.app.inventoryservice.exception.MedicamentNotFoundException;
import com.app.inventoryservice.mapper.MedicamentMapper;
import com.app.inventoryservice.repository.MedicamentRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.inventoryservice.request.MedicamentRequest;
import com.app.inventoryservice.response.MedicamentResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentService {

    private final MedicamentRepository repository;
    private final MedicamentMapper mapper;

    public Integer createMedicament(MedicamentRequest request) {
        Medicament medicament = repository.save(mapper.toMedicament(request));
        return medicament.getId();
    }

    public void updateMedicament(MedicamentRequest request) {
        Medicament medicament = repository.findById(request.id())
                .orElseThrow(() -> new MedicamentNotFoundException(
                        String.format("Cannot update Medicament: No Medicament found with the provided ID: %s", request.id())
                ));
        mergeMedicament(medicament, request);
        repository.save(medicament);
    }

    public void deleteMedicament(Integer id) {
        if (!existsById(id)) {
            throw new MedicamentNotFoundException(
                    String.format("Cannot delete Medicament: No Medicament found with the provided ID: %s", id)
            );
        }
        repository.deleteById(id);
    }

    public List<MedicamentResponse> findAllMedicaments() {
        return repository.findAll()
                .stream()
                .map(mapper::fromMedicament)
                .collect(Collectors.toList());
    }

    public MedicamentResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::fromMedicament)
                .orElseThrow(() -> new MedicamentNotFoundException(
                        String.format("No Medicament found with the provided ID: %s", id)
                ));
    }

    private void mergeMedicament(Medicament medicament, MedicamentRequest request) {
        if (StringUtils.isNotBlank(request.nom())) {
            medicament.setNom(request.nom());
        }
        if (StringUtils.isNotBlank(request.description())) {
            medicament.setDescription(request.description());
        }
        if (request.prix() != null) {
            medicament.setPrix(request.prix());
        }
        if (request.quantiteEnStock() != null) {
            medicament.setQuantiteEnStock(request.quantiteEnStock());
        }
        if (request.dateExpiration() != null) {
            medicament.setDateExpiration(request.dateExpiration());
        }
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }


}
