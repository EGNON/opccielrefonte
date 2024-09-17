package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation,Long> {
    
}
