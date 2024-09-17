package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.TypeFormule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeFormuleDao extends JpaRepository<TypeFormule,String> {
//    TypeFormule findByCodeTypeFormuleIgnoreCase(String code);
    Optional<TypeFormule> findByCodeTypeFormuleIgnoreCase(String code);
    Page<TypeFormule> findBySupprimer(boolean supprimer, Pageable pageable);
    List<TypeFormule> findBySupprimerOrderByCodeTypeFormuleAsc(boolean supprimer);
}
