package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeGarant;
import com.ged.entity.titresciel.TypeObligation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeGarantDao extends JpaRepository<TypeGarant, Long> {
    Optional<TypeGarant> findByCodeTypeGarantIgnoreCase(String code);
}
