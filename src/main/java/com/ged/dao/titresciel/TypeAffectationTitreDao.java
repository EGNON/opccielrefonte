package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeAffectationTitre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeAffectationTitreDao extends JpaRepository<TypeAffectationTitre, Long> {
    Boolean existsByLibelleTypeAffectationIgnoreCase(String libelle);
    Optional<TypeAffectationTitre> findByLibelleTypeAffectationIgnoreCase(String libelle);
}
