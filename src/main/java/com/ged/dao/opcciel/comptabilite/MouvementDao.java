package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MouvementDao extends JpaRepository<Mouvement,Long> {
    @Query(value = "select j.codeJournal from Mouvement m inner join m.operation op inner join Journal j " +
            "on trim(j.numCompteComptable) = trim(m.numCompteComptable) " +
            "where op.idOperation = :id")
    String getCodeJournalByIdOp(@Param("id") Long idOp);
}
