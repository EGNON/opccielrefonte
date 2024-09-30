package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleCompteComptable;
import com.ged.entity.opcciel.comptabilite.CompteComptable;
import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompteComptableDao extends JpaRepository<CompteComptable, CleCompteComptable> {
    Optional<CompteComptable> findByIdCompteComptable(CleCompteComptable id);
    CompteComptable findByNumCompteComptable(String numCompteComptable);
    Page<CompteComptable> findByPlanAndSupprimerOrderByNumCompteComptableAsc(Plan plan, boolean supprimer, Pageable pageable);
    Page<CompteComptable> findBySupprimerOrderByNumCompteComptableAsc(boolean supprimer, Pageable pageable);
    List<CompteComptable> findBySupprimerOrderByNumCompteComptableAsc(boolean supprimer);
    List<CompteComptable> findByPlanAndSupprimerOrderByNumCompteComptableAsc(Plan plan, boolean supprimer);
    List<CompteComptable> findByPlanAndEstMvtAndSupprimerOrderByNumCompteComptableAsc(Plan plan,boolean estMvt,boolean supprimer);

    @Query(value = "select c from CompteComptable c " +
            "where(c.libelleCompteComptable like %:valeur% or c.numCompteComptable like %:valeur% " +
            "or c.plan.libellePlan like %:valeur%) " +
            "and c.supprimer=false")
    Page<CompteComptable> rechercher(String valeur,Pageable pageable);
}
