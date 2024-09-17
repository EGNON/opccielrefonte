package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.CategorieClient;
import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import com.ged.entity.standard.revuecompte.NumeroCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoriqueNumeroCompteDao extends JpaRepository<HistoriqueNumeroCompte, Long> {
    @Query(value = "select h from HistoriqueNumeroCompte h " +
            "where (h.codeSousTypeClient like %:valeur% or h.codeCategorieClient like %:valeur% " +
            "or h.codeSousTypeCompte like %:valeur% or h.codeAgence like %:valeur% or " +
            "h.codeTeneurCompte like %:valeur% " +
            "or h.personne.denomination like %:valeur%) " +
            "and h.supprimer=false")
    Page<HistoriqueNumeroCompte> rechercher(String valeur, Pageable pageable);
}
