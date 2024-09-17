package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeAmortissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeAmortissementDao extends JpaRepository<TypeAmortissement, Long> {
    Optional<TypeAmortissement> findByCodeTypeAmortissementIgnoreCase(String code);
    Optional<TypeAmortissement> findByLibelleTypeAmortissementIgnoreCase(String libelle);
}
