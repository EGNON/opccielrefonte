package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationConstatationCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationConstatationChargeDao extends JpaRepository<OperationConstatationCharge, Long> {
    @Query(value = "select op from OperationConstatationCharge op " +
            "where op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false")
    Page<OperationConstatationCharge> afficherListe(
            @Param("idOpcvm") Long idOpcvm,
            Pageable pageable
    );
}
