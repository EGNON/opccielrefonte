package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleCorrespondance;
import com.ged.entity.opcciel.comptabilite.Correspondance;
import com.ged.projection.CorrespondanceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorrespondanceDao extends JpaRepository<Correspondance, CleCorrespondance> {
    @Query(value = "SELECT  " +
            "cr.numeroCompteComptable as numeroCompteComptable,"+
            "c.libelleCompteComptable as libelleCompteComptable,"+
            "cr.totalBlocage as totalBlocage,"+
            "cr.valeur as valeur,"+
            "cr.plan as plan,"+
            "cr.ib as ib,"+
            "cr.codeRubrique as codeRubrique,"+
            "cr.codePosition as codePosition, "+
            "i.libellePosition as libellePosition "+
            "from Correspondance as cr " +
            "left outer join CompteComptable c on trim(c.numCompteComptable)=trim(cr.numeroCompteComptable) "+
            "left outer join IbRubriquePosition i on trim(i.codeRubrique)=trim(cr.codeRubrique) " +
            "where cr.supprimer=false and c.supprimer=false and i.supprimer=false")
    Page<CorrespondanceProjection> afficherCorrespondance(Pageable pageable);

    @Query(value = "SELECT  " +
            "cr.numeroCompteComptable as numeroCompteComptable,"+
            "c.libelleCompteComptable as libelleCompteComptable,"+
            "cr.totalBlocage as totalBlocage,"+
            "cr.valeur as valeur,"+
            "cr.plan as plan,"+
            "cr.ib as ib,"+
            "cr.codeRubrique as codeRubrique,"+
            "cr.codePosition as codePosition, "+
            "i.libellePosition as libellePosition "+
            "from Correspondance as cr " +
            "inner join CompteComptable c on trim(c.numCompteComptable)=trim(cr.numeroCompteComptable) "+
            "inner join IbRubriquePosition i on trim(i.codeRubrique)=trim(cr.codeRubrique) " +
            "where (cr.supprimer=false and c.supprimer=false and i.supprimer=false) " +
            "and(cr.codePosition like %:valeur% or cr.codeRubrique like %:valeur% " +
            "or cr.numeroCompteComptable like %:valeur% or cr.ib.libelleIb like %:valeur% " +
            "or cr.ib.codeIB like %:valeur% or cr.plan.libellePlan like %:valeur%) " +
            "or c.libelleCompteComptable like %:valeur% or i.libellePosition like %:valeur%")
    Page<CorrespondanceProjection> rechercher(String valeur,Pageable pageable);
}
