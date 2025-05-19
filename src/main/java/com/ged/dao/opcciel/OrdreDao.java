package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Charge;
import com.ged.entity.opcciel.CleCharge;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.projection.ChargeProjection;
import com.ged.projection.OrdreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrdreDao extends JpaRepository<Ordre, Long> {
    Page<Ordre> findByOpcvmAndEstEnvoyeAndSupprimer(Opcvm opcvm,boolean estEnvoye,boolean supprimer, Pageable pageable);
    List<Ordre> findByOpcvmAndEstEnvoyeAndSupprimer(Opcvm opcvm,boolean estEnvoye,boolean supprimer);
    @Query(value = "SELECT t_o.idOrdre as idOrdre" +
            ",t_o.opcvm.idOpcvm as idOpcvm" +
            ",t_o.idTitre as idTitre" +
            ",t_t.symbolTitre as symbolTitre" +
            ",t_t.libelleCotation as libelleCotation" +
            ",t_o.role as role" +
            ",t_o.dateOrdre as dateOrdre" +
            ",t_o.statut as statut" +
            ",t_o.quantiteLimite as quantiteLimite" +
            ",t_o.dateEnvoi as dateEnvoi" +
            ",t_o.dateLimite as dateLimite" +
            ",t_o.coursLimite as coursLimite" +
            ",t_o.accepterPerte as accepterPerte" +
            ",t_o.typeOrdre.libelleTypeOrdre as libelleTypeOrdre" +
            ",t_o.estEnvoye as estEnvoye" +
            ",t_o.commissionPlace as commissionPlace" +
            ",t_o.commissionSGI as commissionSGI" +
            ",t_o.commissionDepositaire as commissionDepositaire" +
            ",t_o.tAF as tAF" +
            ",t_o.iRVM as iRVM" +
            ",t_o.interet as interet" +
            ",t_o.plusOuMoinsValue as plusOuMoinsValue" +
            ",t_o.quantiteExecute as quantiteExecute" +
            ",t_o.montantNet as montantNet" +
            ",t_o.montantBrut as montantBrut" +
            ",t_o.commentaires as commentaires" +
            ",t_o.libelleOperation as libelleOperation " +
            "FROM Ordre t_o " +
            "inner join Titre t_t on t_o.titre.idTitre=t_t.idTitre " +
            "inner join Depositaire t_d " +
            "on t_t.depositaire.idPersonne=t_d.idPersonne " +
            "WHERE (t_o.opcvm.idOpcvm =:idOpcvm) " +
            "and (t_o.idSeance =:idSeance) " +
            "and t_o.estEnvoye =true " +
            "and t_o.supprimer = false " +
            "order by t_o.opcvm.idOpcvm, t_o.idSeance,t_o.role, t_o.idOrdre")
    List<OrdreProjection> impressionOrdreDeBourse(Long idOpcvm,Long idSeance);

    @Query(value = "select o from Ordre o " +
            "WHERE (o.opcvm.idOpcvm =:idOpcvm) " +
            "and o.quantiteLimite>o.quantiteExecute " +
            "and o.estEnvoye =true " +
            "and o.supprimer = false " +
            "and o.statut!='RELACHE'")
    Page<Ordre> ordreEnCours(Long idOpcvm,Pageable pageable);
}
