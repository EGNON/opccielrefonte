package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeEmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeEmissionDao extends JpaRepository<TypeEmission,Long> {
    Optional<TypeEmission> findByLibelleTypeEmissionIgnoreCase(String libelle);
}
