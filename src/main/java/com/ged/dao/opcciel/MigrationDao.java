package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Migration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MigrationDao extends JpaRepository<Migration, Long> {
}
