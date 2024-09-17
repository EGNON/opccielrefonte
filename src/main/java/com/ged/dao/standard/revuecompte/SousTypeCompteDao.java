package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.SousTypeClient;
import com.ged.entity.standard.revuecompte.SousTypeCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SousTypeCompteDao extends JpaRepository<SousTypeCompte, Long> {
    @Query(value = "select s from SousTypeCompte s " +
            "where (s.code like %:valeur% or s.libelleSousTypeCompte like %:valeur% " +
            "or s.typeCompte.libelleTypeCompte like %:valeur%) and s.supprimer=false")
    Page<SousTypeCompte> rechercher(String valeur, Pageable pageable);
}
