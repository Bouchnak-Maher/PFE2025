package com.app.inventoryservice.repository;

import com.app.inventoryservice.model.Facture;
import com.app.inventoryservice.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface FactureRepository extends JpaRepository<Facture, Long> {
        Optional<Facture> findByTacheId(Long tacheId);
    @Query("SELECT f FROM Factures f " +
            "JOIN f.reclamation r " +
            "JOIN r.tache t " +
            "WHERE t.userId = :userId")
    List<Facture> findByReclamationTacheUserId(String userId);
    }



