package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Depositaire;
import com.ged.entity.titresciel.QualiteTitre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface QualiteTitreDao extends JpaRepository<QualiteTitre,Long> {

}
