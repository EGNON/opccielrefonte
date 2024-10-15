package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ActionnaireCommission;
import com.ged.entity.standard.CleActionnaireCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionnaireCommissionDao extends JpaRepository<ActionnaireCommission,CleActionnaireCommission> {
    Page<ActionnaireCommission> findByOpcvm(Opcvm opcvm, Pageable pageable);
    @Query("select a from ActionnaireCommission a " +
            "where a.opcvm.idOpcvm=:idOpcvm  " +
            "order by a.personne.denomination asc")
    Page<ActionnaireCommission> afficherSelonOpcvm(long idOpcvm,Pageable pageable);

    @Query("select a from ActionnaireCommission a " +
            "where a.opcvm.idOpcvm=:idOpcvm and " +
            "(a.personne.denomination like %:valeur% or a.libelleProfil like %:valeur%" +
            " or a.typeCommission like %:valeur%) " +
            "order by a.personne.denomination asc")
    Page<ActionnaireCommission> rechercher(long idOpcvm,String valeur,Pageable pageable);
}
