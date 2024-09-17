package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Action;
import com.ged.entity.titresciel.Opc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface OpcDao extends JpaRepository<Opc, Long>, JpaSpecificationExecutor<Opc> {
    Optional<Opc> findByTypeVMAndIdOcc(String type, Long id);
}
