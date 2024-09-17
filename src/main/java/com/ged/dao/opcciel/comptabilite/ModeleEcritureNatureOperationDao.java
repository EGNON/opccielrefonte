package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModeleEcritureNatureOperationDao extends JpaRepository<ModeleEcritureNatureOperation, Long> {
    /*ModeleEcritureNatureOperation findByModeleEcritureAndNatureOperationAndTypeTitre(ModeleEcriture modeleEcriture,
                                                                                     NatureOperation natureOperation,
                                                                                     TypeTitre typeTitre);*/
    @Query(value = "select m from ModeleEcritureNatureOperation m " +
            "where(m.modeleEcriture.codeModeleEcriture like %:valeur% or " +
            "m.modeleEcriture.libelleModeleEcriture like %:valeur% " +
//            "m.typeTitre.libelleTypeTitre like %:valeur% or m.natureOperation.libelleNatureOperation " +
//            "like %:valeur% " +
            "or m.natureOperation.typeOperation.libelleTypeOperation like %:valeur%)")
    Page<ModeleEcritureNatureOperation> rechercher(String valeur, Pageable pageable);
}
