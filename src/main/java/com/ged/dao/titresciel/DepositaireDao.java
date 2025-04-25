package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Depositaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface DepositaireDao extends JpaRepository<Depositaire,Long> {
    Depositaire findByIdPersonne(Long id);
    /*Optional<Depositaire> findByIdOcc(BigDecimal id);
    Optional<Depositaire> findByTypePersonneAndIdOcc(String type, Long id);*/
}
