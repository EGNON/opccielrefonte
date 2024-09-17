package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementDao extends JpaRepository<Mouvement,Long> {
    
}
