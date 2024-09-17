package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleOperationCodeAnalytique;
import com.ged.entity.opcciel.comptabilite.OperationCodeAnalytique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationCodeAnalytiqueDao extends JpaRepository<OperationCodeAnalytique, CleOperationCodeAnalytique> {
    
}
