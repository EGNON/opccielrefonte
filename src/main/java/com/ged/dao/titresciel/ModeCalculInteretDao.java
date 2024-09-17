package com.ged.dao.titresciel;

import com.ged.entity.titresciel.ModeCalculInteret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ModeCalculInteretDao extends JpaRepository<ModeCalculInteret, Long>, JpaSpecificationExecutor<ModeCalculInteret> {
    Optional<ModeCalculInteret> findByCodeModeCalculInteretIgnoreCase(String code);
}
