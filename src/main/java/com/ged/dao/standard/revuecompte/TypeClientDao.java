package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.SousTypeClient;
import com.ged.entity.standard.revuecompte.TypeClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeClientDao extends JpaRepository<TypeClient, Long> {
    @Query(value = "select t from TypeClient t " +
            "where (t.code like %:valeur% or t.libelleTypeClient like %:valeur%) " +
            "and t.supprimer=false")
    Page<TypeClient> rechercher(String valeur, Pageable pageable);
}
