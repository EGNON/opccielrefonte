package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleIbRubrique;
import com.ged.entity.opcciel.comptabilite.IbRubrique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IbRubriqueDao extends JpaRepository<IbRubrique, CleIbRubrique> {
    Optional<IbRubrique> findByIdIbRubrique(CleIbRubrique id);
}
