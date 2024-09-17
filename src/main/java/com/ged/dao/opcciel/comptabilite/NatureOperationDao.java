package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.TypeOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NatureOperationDao extends JpaRepository<NatureOperation,String> {
    Optional<NatureOperation> findByCodeNatureOperationIgnoreCase(String code);
    List<NatureOperation> findByTypeOperationAndSupprimer(TypeOperation typeOperation,boolean supprimer);
    Page<NatureOperation> findBySupprimer(boolean supprimer, Pageable pageable);
    List<NatureOperation> findBySupprimerOrderByLibelleNatureOperationAsc(boolean supprimer);

    @Query(value = "select n from NatureOperation n " +
            "where(n.codeNatureOperation like %:valeur% or n.libelleNatureOperation like %:valeur%) " +
            "and n.supprimer=false")
    Page<NatureOperation> rechercher(String valeur,Pageable pageable);
}
