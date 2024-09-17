package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.TypeRubrique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRubriqueDao extends JpaRepository<TypeRubrique, String> {
    Optional<TypeRubrique> findByCodeTypeRubriqueIgnoreCase(String code);
    Page<TypeRubrique> findBySupprimer(boolean supprimer, Pageable pageable);
    List<TypeRubrique> findBySupprimerOrderByCodeTypeRubriqueAsc(boolean supprimer);
}
