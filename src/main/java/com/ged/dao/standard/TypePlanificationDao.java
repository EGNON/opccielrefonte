package com.ged.dao.standard;

import com.ged.entity.standard.TypePlanification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePlanificationDao extends JpaRepository<TypePlanification,Long> {
    Boolean existsByLibelleTypePlanification(String libelle);
}
