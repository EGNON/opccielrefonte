package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Formule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormuleDao extends JpaRepository<Formule, Long> {
    Formule findByLibelleFormuleIgnoreCase(String libelle);
    Formule findByIdOcc(Long id);

    Page<Formule> findBySupprimer(boolean supprimer,Pageable pageable);
    List<Formule> findBySupprimerOrderByLibelleFormuleAsc(boolean supprimer);

    @Query(value = "select f from Formule f " +
            "where (f.libelleFormule like %:valeur% or f.typeFormule.codeTypeFormule like %:valeur%) " +
            "and f.supprimer=false")
    Page<Formule> rechercher(String valeur,Pageable pageable);
}
