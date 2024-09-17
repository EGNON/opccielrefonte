package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.ClasseIb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClasseIbDao extends JpaRepository<ClasseIb, String> {
    Optional<ClasseIb> findByCodeClasseIbIgnoreCase(String code);
}
