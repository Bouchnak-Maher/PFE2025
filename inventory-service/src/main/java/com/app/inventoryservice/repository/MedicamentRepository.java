package com.app.inventoryservice.repository;

import com.app.inventoryservice.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
}
