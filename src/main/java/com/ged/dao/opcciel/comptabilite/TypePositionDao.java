package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.TypePosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypePositionDao extends JpaRepository<TypePosition, String> {
    Optional<TypePosition> findByCodeTypePositionIgnoreCase(String code);
    Page<TypePosition> findBySupprimer(boolean supprimer, Pageable pageable);
    List<TypePosition> findBySupprimerOrderByLibelleTypePositionAsc(boolean supprimer);
}
