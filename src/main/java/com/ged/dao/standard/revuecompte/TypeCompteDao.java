package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.TypeCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeCompteDao extends JpaRepository<TypeCompte, Long> {
    @Query(value = "select t from TypeCompte t " +
            "where (t.code like %:valeur% or t.intitule like %:valeur%) " +
            "and t.supprimer=false")
    Page<TypeCompte> rechercher(String valeur, Pageable pageable);
}
