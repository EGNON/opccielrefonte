
package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PosteComptableDao extends JpaRepository<PosteComptable, ClePosteComptable> {
   PosteComptable findByCodePosteComptable(String code);
   Page<PosteComptable> findBySupprimer(boolean supprimer, Pageable pageable);
   List<PosteComptable> findBySupprimerOrderByLibellePosteComptableAsc(boolean supprimer);
   List<PosteComptable> findBySupprimerAndPlan(boolean supprimer,Plan plan);

   @Query(value = "select p from PosteComptable p " +
           "where (p.libellePosteComptable like %:valeur% or p.codePosteComptable like %:valeur% " +
           "or p.type like %:valeur% or p.plan.libellePlan like %:valeur% or p.formule like %:valeur%) " +
           "and p.supprimer=false")
   Page<PosteComptable> rechercher(String valeur,Pageable pageable);
}
