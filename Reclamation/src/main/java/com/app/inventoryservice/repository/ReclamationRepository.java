package com.app.inventoryservice.repository;

import com.app.inventoryservice.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByCreatedBy(String createdBy);

    @Query("SELECT COUNT(DISTINCT r.createdBy) FROM Reclamation r")
    long countDistinctEmployeesWithReclamations();
}

