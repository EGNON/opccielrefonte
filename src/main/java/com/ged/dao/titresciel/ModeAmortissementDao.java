package com.ged.dao.titresciel;

import com.ged.entity.titresciel.ModeAmortissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeAmortissementDao extends JpaRepository<ModeAmortissement, Long> {
    Optional<ModeAmortissement> findByLibelleModeAmortissementIgnoreCase(String libelle);
}
