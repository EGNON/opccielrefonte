package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.CleNumeroCompte;
import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import com.ged.entity.standard.revuecompte.NumeroCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NumeroCompteDao extends JpaRepository<NumeroCompte, CleNumeroCompte> {
    @Query(value = "select n from NumeroCompte n " +
            "where (n.codeSousTypeClient like %:valeur% or n.codeCategorieClient like %:valeur% " +
            "or n.codeSousTypeCompte like %:valeur% or n.codeAgence like %:valeur% or " +
            "n.codeTeneurCompte like %:valeur% or n.sousTypeCompte.intitule like %:valeur% " +
            "or n.personne.denomination like %:valeur%) " +
            "and n.supprimer=false ")
    Page<NumeroCompte> rechercher(String valeur, Pageable pageable);
}
