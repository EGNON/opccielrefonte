package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.TypeIb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeIbDao extends JpaRepository<TypeIb, String> {
    Optional<TypeIb> findByCodeTypeIbIgnoreCase(String code);
    Page<TypeIb> findBySupprimer(boolean supprimer, Pageable pageable);
    List<TypeIb> findBySupprimerOrderByLibelleTypeIBAsc(boolean supprimer);
}
