package com.ged.dao.titresciel;

import com.ged.entity.titresciel.NatureTcn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NatureTcnDao extends JpaRepository<NatureTcn, Long> {
    Optional<NatureTcn> findByCodeNatureTcnIgnoreCase(String code);
    Optional<NatureTcn> findByLibelleNatureTcnIgnoreCase(String libelle);
}
