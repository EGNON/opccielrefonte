package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationPaiementCharge;
import com.ged.entity.opcciel.OperationRegulEcartSolde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRegulEcartSoldeDao extends JpaRepository<OperationRegulEcartSolde, Long> {
    @Query(value = "select op from OperationRegulEcartSolde op " +
            "where op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false order by op.idOperation desc")
    Page<OperationRegulEcartSolde> afficherListe(
            @Param("idOpcvm") Long idOpcvm,
            Pageable pageable
    );
    @Query("select op from OperationRegulEcartSolde op " +
            "where op.idOperation=:idOperation")
    OperationRegulEcartSolde afficherSelonId(Long idOperation);
    @Query("delete from OperationRegulEcartSolde o " +
            "where o.idOperation=:idOperation")
    @Modifying()
    int supprimer(Long idOperation);

}
