package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.projection.OperationConstatationChargeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationConstatationChargeDao extends JpaRepository<OperationConstatationCharge, Long> {
    @Query(value = "select op from OperationConstatationCharge op " +
            "where op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false order by op.idOperation desc")
    Page<OperationConstatationCharge> afficherListe(
            @Param("idOpcvm") Long idOpcvm,
            Pageable pageable
    );
    @Query(value = "select op from OperationConstatationCharge op " +
            "where op.idOperation= :idOperation and op.supprimer = false order by op.idOperation desc")
   OperationConstatationCharge afficherSelonId(
            @Param("idOperation") Long idOperation
    );

    @Query(value = "select op.idOperation as idOperation" +
            ",op.idTransaction as idTransaction" +
            ",op.idSeance as idSeance" +
            ",op.idSeancePaiement as idSeancePaiement" +
            ",op.opcvm.idOpcvm as idOpcvm" +
            ",op.natureOperation.codeNatureOperation as codeNatureOperation" +
            ",op.dateSolde as dateSolde" +
            ",op.dateOperation as dateOperation" +
            ",op.libelleOperation as libelleOperation" +
            ",c.designation as designation" +
            ",op.dateSaisie as dateSaisie" +
            ",op.datePiece as datePiece" +
            ",op.dateValeur as dateValeur" +
            ",op.referencePiece as referencePiece" +
            ",op.montant as montant" +
            ",op.montantCharge as montantCharge" +
            ",op.codeCharge as codeCharge" +
            ",op.estPayee as estPayee" +
            " from OperationConstatationCharge op " +
            "inner join Charge c on c.idCharge.codeCharge=op.codeCharge and c.idCharge.idOpcvm=op.opcvm.idOpcvm " +
            "where (op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false and op.estPayee=false)" +
            " order by op.idOperation desc")
    List<OperationConstatationChargeProjection> afficherListeOperation(
            @Param("idOpcvm") Long idOpcvm
    );
    @Query(value = "select op.idOperation as idOperation" +
            ",op.idTransaction as idTransaction" +
            ",op.idSeance as idSeance" +
            ",op.idSeancePaiement as idSeancePaiement" +
            ",op.opcvm.idOpcvm as idOpcvm" +
            ",op.natureOperation.codeNatureOperation as codeNatureOperation" +
            ",op.dateSolde as dateSolde" +
            ",op.dateOperation as dateOperation" +
            ",op.libelleOperation as libelleOperation" +
            ",c.designation as designation" +
            ",op.dateSaisie as dateSaisie" +
            ",op.datePiece as datePiece" +
            ",op.dateValeur as dateValeur" +
            ",op.referencePiece as referencePiece" +
            ",op.montant as montant" +
            ",op.montantCharge as montantCharge" +
            ",op.codeCharge as codeCharge" +
            ",op.estPayee as estPayee" +
            " from OperationConstatationCharge op " +
            "inner join Charge c on c.idCharge.codeCharge=op.codeCharge and c.idCharge.idOpcvm=op.opcvm.idOpcvm " +
            "where (op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false and op.estPayee=false)" +
            " or (op.opcvm.idOpcvm = :idOpcvm and op.supprimer = false and op.idSeance=:idSeance and op.estPayee=true)" +
            " order by op.idOperation desc")
    List<OperationConstatationChargeProjection> afficherListeOperation(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idSeance") Long idSeance
    );
    @Query("update OperationConstatationCharge o " +
            "set o.idSeancePaiement=:idSeancePaiement," +
            "o.estPayee=true " +
            "where o.idOperation=:idOperation")
    @Modifying()
    int modifier(Long idSeancePaiement,Long idOperation);
}
