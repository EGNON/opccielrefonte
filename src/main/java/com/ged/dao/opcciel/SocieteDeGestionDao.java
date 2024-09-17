package com.ged.dao.opcciel;

import com.ged.entity.opcciel.SocieteDeGestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocieteDeGestionDao extends JpaRepository<SocieteDeGestion,Long> {
    Page<SocieteDeGestion> findByDenominationContainsIgnoreCase(String denomination, Pageable pageable);
    SocieteDeGestion findById(long id);
    Optional<SocieteDeGestion> findByTypePersonneAndIdOcc(String type, Long id);
}
