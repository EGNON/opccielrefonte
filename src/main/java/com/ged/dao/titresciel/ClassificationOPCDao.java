package com.ged.dao.titresciel;

import com.ged.entity.titresciel.ClassificationOPC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ClassificationOPCDao extends JpaRepository<ClassificationOPC,String> {
    Boolean existsByCodeClassificationIgnoreCase(String code);
    Boolean existsByLibelleClassificationIgnoreCase(String libelle);
    Optional<ClassificationOPC> findByLibelleClassificationIgnoreCase(String libelle);
    Optional<ClassificationOPC> findByCodeClassificationIgnoreCase(String code);
    Page<ClassificationOPC> findByLibelleClassificationContainsIgnoreCase(String libelle, Pageable pageable);
}
