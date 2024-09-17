package com.ged.dao.standard;
import com.ged.entity.standard.NbreJours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbreJoursDao extends JpaRepository<NbreJours, Long> {
    Boolean existsByIdNbreJours(Long id);
}
