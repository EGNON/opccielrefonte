package com.ged.dao.titresciel;

import com.ged.entity.titresciel.CleStatutTitre;
import com.ged.entity.titresciel.StatutTitre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutTitreDao extends JpaRepository<StatutTitre, CleStatutTitre> {
}
