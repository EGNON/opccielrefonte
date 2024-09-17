package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction,Long> {
    
}
