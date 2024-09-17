package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Registraire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface RegistraireDao extends JpaRepository<Registraire,Long> {
//    Optional<Registraire> findByIdOcc(BigDecimal id);
//    Optional<Registraire> findByTypePersonneAndIdOcc(String type, Long id);
}
