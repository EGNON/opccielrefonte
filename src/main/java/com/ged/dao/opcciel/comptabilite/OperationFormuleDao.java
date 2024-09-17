package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleOperationFormule;
import com.ged.entity.opcciel.comptabilite.OperationFormule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationFormuleDao extends JpaRepository<OperationFormule, CleOperationFormule> {
    
}
