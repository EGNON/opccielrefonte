package com.ged.dao.standard.revuecompte;

import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SousTypeClientDao extends JpaRepository<SousTypeClient, Long> {
    @Query(value = "select s " +
                    " from SousTypeClient s " +
                    "where s.typeClient.intitule like '%personne physique%' ")
    List<SousTypeClient> afficherPersonnePhysique();
    @Query(value = "select s " +
                    " from SousTypeClient s " +
                    "where s.typeClient.intitule not like '%personne physique%' ")
    List<SousTypeClient> afficherAutresTypeCLient();

    @Query(value = "select s from SousTypeClient s " +
            "where (s.code like %:valeur% or s.intitule like %:valeur% " +
            "or s.typeClient.intitule like %:valeur%) and s.supprimer=false")
    Page<SousTypeClient> rechercher(String valeur, Pageable pageable);
}
