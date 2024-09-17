package com.ged.dao.standard;

import com.ged.entity.standard.ModeEtablissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ModeEtablissementDao extends JpaRepository<ModeEtablissement,Long> {
    boolean existsByLibelle(String libelle);
    Page<ModeEtablissement> findByLibelleContainsIgnoreCase(String libelle, Pageable pageable);
}
