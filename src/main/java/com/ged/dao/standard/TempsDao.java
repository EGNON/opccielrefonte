package com.ged.dao.standard;

import com.ged.entity.standard.Temps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempsDao extends JpaRepository<Temps,Long> {
    boolean existsByLibelle(String libelle);
}
