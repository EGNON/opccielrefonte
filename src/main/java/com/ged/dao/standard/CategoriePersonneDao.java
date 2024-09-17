package com.ged.dao.standard;

import com.ged.entity.standard.CategoriePersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface CategoriePersonneDao extends JpaRepository<CategoriePersonne,Long> {
    CategoriePersonne findByLibelle(String libelle);
    Boolean existsByLibelle(String libelle);
    Page<CategoriePersonne> findByLibelleContainsIgnoreCase(String libelle, Pageable pageable);
    @Procedure
    int
    GET_TOTAL_CAT_BY_LIBELLE(String libelle);
}
