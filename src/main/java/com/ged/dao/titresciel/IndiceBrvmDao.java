package com.ged.dao.titresciel;

import com.ged.entity.titresciel.CleIndiceBrvm;
import com.ged.entity.titresciel.IndiceBrvm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndiceBrvmDao extends JpaRepository<IndiceBrvm, CleIndiceBrvm> {
}
