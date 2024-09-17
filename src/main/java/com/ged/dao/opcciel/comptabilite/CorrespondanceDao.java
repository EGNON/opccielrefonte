package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleCorrespondance;
import com.ged.entity.opcciel.comptabilite.Correspondance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrespondanceDao extends JpaRepository<Correspondance, CleCorrespondance> {
    /*@Query(value = "SELECT  " +
            "cr.numCompteComptable as numCompteComptable,"+
            "c.libelleCompteComptable as libelleCompteComptable,"+
            "cr.totalBlocage as totalBlocage,"+
            "cr.valeur as valeur,"+
            "cr.plan as plan,"+
            "cr.ib as ib,"+
            "cr.codeRubrique as codeRubrique,"+
            "cr.codePosition as codePosition, "+
            "i.libellePosition as libellePosition "+
            "from Correspondance as cr " +
            "inner join CompteComptable c on c.numCompteComptable=cr.numCompteComptable "+
            "inner join IbRubriquePosition i on i.codeRubrique=cr.codeRubrique " +
            "where cr.supprimer=false and c.supprimer=false and i.supprimer=false")
    Page<CorrespondanceProjection> afficherCorrespondance(Pageable pageable);

    @Query(value = "SELECT  " +
            "cr.numCompteComptable as numCompteComptable,"+
            "c.libelleCompteComptable as libelleCompteComptable,"+
            "cr.totalBlocage as totalBlocage,"+
            "cr.valeur as valeur,"+
            "cr.plan as plan,"+
            "cr.ib as ib,"+
            "cr.codeRubrique as codeRubrique,"+
            "cr.codePosition as codePosition, "+
            "i.libellePosition as libellePosition "+
            "from Correspondance as cr " +
            "inner join CompteComptable c on c.numCompteComptable=cr.numCompteComptable "+
            "inner join IbRubriquePosition i on i.codeRubrique=cr.codeRubrique " +
            "where (cr.supprimer=false and c.supprimer=false and i.supprimer=false) " +
            "and(cr.codePosition like %:valeur% or cr.codeRubrique like %:valeur% " +
            "or cr.numCompteComptable like %:valeur% or cr.ib.libelleIb like %:valeur% " +
            "or cr.ib.codeIB like %:valeur% or cr.plan.libellePlan like %:valeur%) " +
            "or c.libelleCompteComptable like %:valeur% or i.libellePosition like %:valeur%")
    Page<CorrespondanceProjection> rechercher(String valeur,Pageable pageable);*/
}
