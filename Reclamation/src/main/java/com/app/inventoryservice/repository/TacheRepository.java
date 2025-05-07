package com.app.inventoryservice.repository;

import com.app.inventoryservice.model.Reclamation;
import com.app.inventoryservice.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByUserId(String userId);
}
