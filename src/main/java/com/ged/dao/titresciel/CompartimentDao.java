package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Compartiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompartimentDao extends JpaRepository<Compartiment,Long> {
    Optional<Compartiment> findByLibelleCompartimentIgnoreCase(String libelle);
}
