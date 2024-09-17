package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.OperationJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationJournalDao extends JpaRepository<OperationJournal,OperationJournal> {
    
}
