package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Action;
import com.ged.entity.titresciel.Dat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

public interface DatDao extends JpaRepository<Dat, Long>, JpaSpecificationExecutor<Dat> {
    Optional<Dat> findByTypeVMAndIdOcc(String type, Long id);
    Dat findByIdTitre(Long id);
}
