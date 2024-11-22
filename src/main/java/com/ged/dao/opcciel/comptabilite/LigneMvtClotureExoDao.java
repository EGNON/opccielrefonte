package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleLigneMvtClotureExo;
import com.ged.entity.opcciel.comptabilite.LigneMvtClotureExo;
import com.ged.projection.LigneMvtClotureProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LigneMvtClotureExoDao extends JpaRepository<LigneMvtClotureExo, CleLigneMvtClotureExo> {
    /*@Query(value = "select l.opcvm.idOpcvm, 0, n.codeNatureOperation, n.libelleNatureOperation, m.codeModeleEcriture, " +
            "m.libelleModeleEcriture, 1L, l.idLigneMvtClotureExo.numeroOrdeEtape, l.ib.codeIB, " +
            "l.codeRubrique, l.codePosition, irp.libellePosition, cc.numCompteComptable, cc.libelleCompteComptable, " +
            "l.plan.codePlan, l.Sens, l.formule.idFormule, l.valeur, l.typeFormule.codeTypeFormule from LigneMvtClotureExo l " +
            "inner join l.natureOperation n inner join l.modeleEcriture m inner join CompteComptable cc " +
            "on trim(cc.idCompteComptable.numCompteComptable) = trim(l.idLigneMvtClotureExo.numeroCompteComptable) " +
            "inner join Exercice ex on trim(ex.idExercie.codeExercice) = trim(l.idLigneMvtClotureExo.codeExercice) " +
            "and ex.opcvm.idOpcvm = l.opcvm.idOpcvm left outer join IbRubriquePosition irp " +
            "on trim(irp.idIbRubriquePosition.codeRubrique) = trim(l.codeRubrique) " +
            "and trim(irp.idIbRubriquePosition.codePosition) = trim(l.codePosition) " +
            "and trim(irp.idIbRubriquePosition.codeIB) = trim(l.ib.codeIB) where ex.estCourant = true " +
            "and l.opcvm.idOpcvm = :idOpcvm and trim(l.natureOperation.codeNatureOperation) = trim(:code)")
    List<LigneMvtClotureProjection> listeLigneMvtClotureExo(@Param("idOpcvm") Long idOpcvm, @Param("code") String code);*/
}
