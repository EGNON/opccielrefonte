package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiseEnAffectationDao extends JpaRepository<ModeleEcriture,String> {
    
}
