package com.ged.dao.titresciel;

import com.ged.entity.titresciel.NatureEvenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NatureEvenementDao extends JpaRepository<NatureEvenement,Long> {
    Optional<NatureEvenement> findByLibelleNatureEvenementIgnoreCase(String libelle);
}
