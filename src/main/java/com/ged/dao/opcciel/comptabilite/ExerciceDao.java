package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciceDao extends JpaRepository<Exercice, CleExercice> {
    @Query("select distinct e "+
            "from Exercice e")
    List<Exercice> afficherExercice();
}
