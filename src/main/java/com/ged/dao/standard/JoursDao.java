package com.ged.dao.standard;
import com.ged.entity.standard.Jours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoursDao extends JpaRepository<Jours, Long> {
    Boolean existsByLibelleJours(String libelle);
}
