package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Action;
import com.ged.entity.titresciel.Obligation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface ActionDao extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {
    Optional<Action> findByTypeVMAndIdOcc(String type, Long id);
}
