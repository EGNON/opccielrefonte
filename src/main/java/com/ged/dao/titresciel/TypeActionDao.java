package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeActionDao extends JpaRepository<TypeAction, Long> {
    Optional<TypeAction> findByCodeTypeActionIgnoreCase(String code);
}
