package com.ged.dao.titresciel;

import com.ged.entity.titresciel.SousTypeAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SousTypeActionDao extends JpaRepository<SousTypeAction, Long> {
    Optional<SousTypeAction> findByCodeSousTypeActionIgnoreCase(String code);
}
