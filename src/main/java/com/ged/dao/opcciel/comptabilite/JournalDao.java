package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JournalDao extends JpaRepository<Journal,String> {
    Optional<Journal> findByCodeJournalIgnoreCase(String code);
    /*@Query(value = "SELECT  " +
            "j.codeJournal as codeJournal,"+
            "j.libelleJournal as libelleJournal,"+
            "j.plan as plan,"+
            "j.numCompteComptable "+
            "from Journal as j " +
            "left outer join Plan p on p.codePlan=j.plan.codePlan "+
            "left outer join CompteComptable c on c.numCompteComptable=j.numCompteComptable " +
            "")
    Page<JournalProjection> afficherJournal(Pageable pageable);
    Page<Journal> findBySupprimer(boolean supprimer,Pageable pageable);

    @Query(value = "SELECT  " +
            "j.codeJournal as codeJournal,"+
            "j.libelleJournal as libelleJournal,"+
            "j.plan as plan,"+
            "j.numCompteComptable "+
            "from Journal as j " +
            "left outer join Plan p on p.codePlan=j.plan.codePlan "+
            "left outer join CompteComptable c on c.numCompteComptable=j.numCompteComptable " +
            "where j.supprimer=false " +
            "order by j.libelleJournal ASC ")
    List<JournalProjection> afficherJournal();*/
    List<Journal> findBySupprimerOrderByLibelleJournalAsc(boolean supprimer);
    @Query(value = "select j from Journal j " +
            "where (j.codeJournal like %:valeur% or j.libelleJournal like %:valeur%) " +
            "and j.supprimer=false")
    Page<Journal> rechercher(String valeur,Pageable pageable);
}
