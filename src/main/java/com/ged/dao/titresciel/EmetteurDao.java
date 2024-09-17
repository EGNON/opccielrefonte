package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Emetteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface EmetteurDao extends JpaRepository<Emetteur,Long> {
    Optional<Emetteur> findByTypePersonneAndIdOcc(String type, Long id);
}
