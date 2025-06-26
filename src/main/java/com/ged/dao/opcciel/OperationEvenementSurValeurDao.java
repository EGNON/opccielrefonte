package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.OperationEvenementSurValeur;
import com.ged.projection.OperationDetachementProjection;
import com.ged.projection.OperationEvenementSurValeurProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationEvenementSurValeurDao extends JpaRepository<OperationEvenementSurValeur,Long>, JpaSpecificationExecutor<OperationEvenementSurValeur> {
List<OperationEvenementSurValeur> findBySupprimerAndOpcvm(boolean valeur,Opcvm opcvm);

    @Query(value = "select " +
            "o.codeNatureOperation as codeNatureOperation," +
            "o.typeEvenement as typeEvenement," +
            "o.typePayement as typePayement," +
            "o.idIntervenant as idIntervenant," +
            "o.qteDetenue as qteDetenue," +
            "o.couponDividendeUnitaire as couponDividendeUnitaire," +
            "o.montantBrut as montantBrut," +
            "o.quantiteAmortie as quantiteAmortie," +
            "o.nominalRemb as nominalRemb," +
            "o.capitalRembourse as capitalRembourse," +
            "o.montantTotalARecevoir as montantTotalARecevoir," +
            "o.idOpcvm as idOpcvm," +
            "o.estPaye as estPaye," +
            "o.dateReelle as dateReelle," +
            "o.idTransaction as idTransaction," +
            "o.idOperation as idOperation," +
            "o.opcvm as opcvm," +
            "o.intervenant as personne," +
            "o.montant as montant," +
            "o.estOD as estOD," +
            "o.titre as titre," +
            "o.idSeance as idSeance," +
            "o.natureOperation as natureOperation," +
            "o.dateOperation as dateOperation," +
            "o.libelleOperation as libelleOperation," +
            "o.dateSaisie as dateSaisie," +
            "o.datePiece as datePiece," +
            "o.dateValeur as dateValeur," +
            "o.referencePiece as referencePiece," +
            "o.ecriture as ecriture," +
            "o.operationDetachement as operationDetachement," +
            "o.type as type," +
            "o.interetMoratoireSurCapital as interetMoratoireSurCapital," +
            "o.interetMoratoireSurInteret as interetMoratoireSurInteret," +
            "o.commission as commission," +
            "o.irvm as irvm," +
            "o.commissionSurInteretMoratoire as commissionSurInteretMoratoire " +
            "from OperationEvenementSurValeur o  " +
            "where o.idOperation=:id")
    OperationEvenementSurValeurProjection afficherSelonId(Long id);
}
