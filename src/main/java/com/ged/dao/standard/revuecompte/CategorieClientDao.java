package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.Arrondissement;
import com.ged.entity.standard.revuecompte.CategorieClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieClientDao extends JpaRepository<CategorieClient, Long> {
    @Query(value = "select c from CategorieClient c " +
            "where (c.code like %:valeur% or c.intitule like %:valeur%) " +
            "and c.supprimer=false")
    Page<CategorieClient> rechercher(String valeur, Pageable pageable);
}
