package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciceDao extends JpaRepository<Exercice, CleExercice> {
    
}
