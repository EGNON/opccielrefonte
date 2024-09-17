package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Obligation;
import com.ged.entity.titresciel.Titre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional
public interface ObligationDao extends JpaRepository<Obligation, Long>, JpaSpecificationExecutor<Obligation> {
    Optional<Obligation> findByTypeVMAndIdOcc(String type, Long id);
}
