package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleModeleEcritureFormule;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeleEcritureFormuleDao extends JpaRepository<ModeleEcritureFormule, CleModeleEcritureFormule> {
    void deleteByModeleEcriture(ModeleEcriture modeleEcriture);
    @Query("select m from ModeleEcritureFormule m " +
            "where m.modeleEcriture.codeModeleEcriture=:code and m.supprimer=false")
    List<ModeleEcritureFormule> liste(String code);
    Page<ModeleEcritureFormule> findBySupprimer(boolean supprimer, Pageable pageable);
    List<ModeleEcritureFormule> findBySupprimer(boolean supprimer);
}
