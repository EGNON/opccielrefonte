package com.ged.dao.standard;

import com.ged.entity.standard.Secteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SecteurDao extends JpaRepository<Secteur, Long> {
    Secteur findByLibelleSecteurContains(String keyword);
    Optional<Secteur> findByLibelleSecteurIgnoreCase(String libelle);
    Page<Secteur> findByLibelleSecteurContainsIgnoreCase(String libelle, Pageable pageable);
    Boolean existsByLibelleSecteurIgnoreCase(String libelle);
    @Query(value = "select s from Secteur as s where s.idSecteur = :idSecteur")
    Secteur retrouverSecteurSelonId(@Param("idSecteur") Long idSecteur);
    @Query(value = "select s from Secteur as s where s.libelleSecteur like %:nom%")
    Page<Secteur> rechercherSecteursParNom(@Param("nom") String nom, PageRequest pageRequest);
}
