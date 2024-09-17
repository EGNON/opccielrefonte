package com.ged.dao.titresciel;

import com.ged.entity.titresciel.FormeJuridiqueOpc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormeJuridiqueOpcDao extends JpaRepository<FormeJuridiqueOpc, Long> {
    Optional<FormeJuridiqueOpc> findByCodeFormeJuridiqueOpcIgnoreCase(String code);
}
