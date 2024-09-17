package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeAffectationVL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeAffectationTitreDao extends JpaRepository<TypeAffectationVL, Long> {
    Boolean existsByLibelleTypeAffectationIgnoreCase(String libelle);
    Optional<TypeAffectationVL> findByLibelleTypeAffectationIgnoreCase(String libelle);
}
