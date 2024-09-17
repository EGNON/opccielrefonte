package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleLigneMvtClotureExo;
import com.ged.entity.opcciel.comptabilite.LigneMvtClotureExo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneMvtClotureExoDao extends JpaRepository<LigneMvtClotureExo, CleLigneMvtClotureExo> {
    
}
