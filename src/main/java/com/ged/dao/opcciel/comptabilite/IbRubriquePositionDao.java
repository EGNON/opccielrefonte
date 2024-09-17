package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleIbRubriquePosition;
import com.ged.entity.opcciel.comptabilite.IbRubriquePosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IbRubriquePositionDao extends JpaRepository<IbRubriquePosition, CleIbRubriquePosition> {
    Page<IbRubriquePosition> findBySupprimer(boolean supprimer, Pageable pageable);
    List<IbRubriquePosition> findBySupprimerOrderByIbAsc(boolean supprimer);

    @Query(value = "select i from IbRubriquePosition i " +
            "where (i.ib.codeIB like %:valeur% or i.ib.libelleIb like %:valeur% " +
            "or i.codeRubrique like %:valeur% or i.codePosition like %:valeur% " +
            "or i.libellePosition like %:valeur% or i.typeValeur like %:valeur%) " +
            "and i.supprimer=false")
    Page<IbRubriquePosition> rechercher(String valeur,Pageable pageable);
}
