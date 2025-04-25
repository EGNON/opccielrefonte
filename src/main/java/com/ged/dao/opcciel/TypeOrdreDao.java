package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.opcciel.TypeOrdre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeOrdreDao extends JpaRepository<TypeOrdre, Long> {
    Page<TypeOrdre> findBySupprimer(boolean supprimer,Pageable pageable);
    List<TypeOrdre> findBySupprimerOrderByLibelleTypeOrdre(boolean supprimer);
}
