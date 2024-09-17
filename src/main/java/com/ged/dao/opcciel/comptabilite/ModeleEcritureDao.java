package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeleEcritureDao extends JpaRepository<ModeleEcriture,String> {
    Page<ModeleEcriture> findBySupprimer(boolean supprimer, Pageable pageable);
    List<ModeleEcriture> findBySupprimerOrderByLibelleModeleEcritureAsc(boolean supprimer);

    @Query(value = "select m from ModeleEcriture m " +
            "where (m.codeModeleEcriture like %:valeur% or m.libelleModeleEcriture like %:valeur%) " +
            "and m.supprimer=false ")
    Page<ModeleEcriture> rechercher(String valeur,Pageable pageable);
}
