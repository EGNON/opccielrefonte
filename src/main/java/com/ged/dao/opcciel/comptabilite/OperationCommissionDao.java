package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.OperationCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationCommissionDao extends JpaRepository<OperationCommission, Long> {
    @Query(value = "select op from OperationCommission op " +
            "where op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false order by op.idOperation desc")
    Page<OperationCommission> afficherListe(
            @Param("idOpcvm") Long idOpcvm,
            Pageable pageable
    );
}
