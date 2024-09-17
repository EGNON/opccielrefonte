package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Charge;
import com.ged.entity.opcciel.Opcvm;
import com.ged.projection.ChargeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChargeDao extends JpaRepository<Charge, Long> {
    Page<Charge> findByOpcvm(Opcvm opcvm, Pageable pageable);
    @Query(value = "select c.idCharge as idCharge, " +
            "c.montant as montant," +
            "c.designation as designation," +
            "c.typeCharge as typeCharge," +
            "c.idCharge.codeCharge as codeCharge," +
            "c.natureOperation as natureOperation," +
            "c.appliquerSurActifNet as  appliquerSurActifNet " +
            "from Charge c " +
            "where c.idCharge=:id")
    ChargeProjection rechercherChargeSelonId(Long id);

    @Modifying()
    @Query(value = "update  Charge c set " +
            "c.montant=:montant," +
            "c.designation=:designation," +
            "c.typeCharge=:typeCharge," +
            "c.idCharge.codeCharge=:codeCharge," +
            "c.natureOperation.codeNatureOperation=:codeNatureOperation," +
            "c.appliquerSurActifNet=:appliquerSurActifNet," +
            "c.estActif=true " +
            "where c.idCharge=:id ")
    int modifierCharge (Long id,double montant,String designation,String typeCharge,
                                         String codeCharge,String codeNatureOperation,
                  boolean appliquerSurActifNet);

    @Modifying()
    @Query(value = "delete from  Charge c  " +
            "where c.idCharge=:id ")
    int supprimerCharge (Long id);
}
