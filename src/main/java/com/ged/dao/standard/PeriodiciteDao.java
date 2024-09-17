package com.ged.dao.standard;

import com.ged.entity.standard.Periodicite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodiciteDao extends JpaRepository<Periodicite,Long> {
    Periodicite findByLibelle(String libelle);
    Boolean existsByLibelle(String libelle);
    Page<Periodicite> findByLibelleContainsIgnoreCase(String libelle, Pageable pageable);
}
