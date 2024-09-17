package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeObligation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeObligationDao extends JpaRepository<TypeObligation, Long> {
    Optional<TypeObligation> findByCodeTypeObligationIgnoreCase(String code);
    Optional<TypeObligation> findByLibelleTypeObligationIgnoreCase(String libelle);
}
