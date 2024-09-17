package com.ged.dao.crm;

import com.ged.entity.crm.Affectation;
import com.ged.entity.standard.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffectationDao extends JpaRepository<Affectation,Long> {
    List<Affectation> findByPersonnel(Personnel personnel);
}
