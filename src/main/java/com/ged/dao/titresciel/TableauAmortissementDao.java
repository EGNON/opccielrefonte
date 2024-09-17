package com.ged.dao.titresciel;

import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.entity.titresciel.TableauAmortissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableauAmortissementDao extends JpaRepository<TableauAmortissement, CleTableauAmortissement> {
}
