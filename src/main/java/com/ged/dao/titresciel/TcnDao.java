package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Action;
import com.ged.entity.titresciel.Tcn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface TcnDao extends JpaRepository<Tcn, Long>, JpaSpecificationExecutor<Tcn> {
    Optional<Tcn> findByTypeVMAndIdOcc(String type, Long id);
}
