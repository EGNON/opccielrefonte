
package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlanDao extends JpaRepository<Plan,String> {
    Optional<Plan> findByCodePlanIgnoreCase(String code);
    Page<Plan> findBySupprimer(boolean supprimer, Pageable pageable);
    List<Plan> findBySupprimerOrderByLibellePlanAsc(boolean supprimer);

    @Query(value = "select p from Plan p " +
            "where(p.codePlan like %:valeur% or p.libellePlan like %:valeur%) " +
            "and p.supprimer=false")
    Page<Plan> rechercher(String valeur,Pageable pageable);
}
