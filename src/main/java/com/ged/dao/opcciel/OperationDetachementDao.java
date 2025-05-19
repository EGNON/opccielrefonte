package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.projection.OperationDetachementProjection;
import com.ged.projection.PrecalculRestitutionReliquatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationDetachementDao extends JpaRepository<OperationDetachement,Long>, JpaSpecificationExecutor<OperationDetachement> {
    Page<OperationDetachement> findBySupprimerAndOpcvm(boolean supprimer, Opcvm opcvm, Pageable pageable);
    List<OperationDetachement> findBySupprimerAndOpcvm(boolean supprimer, Opcvm opcvm);

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
            "p as intervenant," +
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
            "o.type " +
            "from OperationDetachement o inner join Personne p on p.idPersonne=o.intervenant.idPersonne " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.supprimer=false")
    Page<OperationDetachementProjection> afficherTous(Long idOpcvm,Pageable pageable);
}
