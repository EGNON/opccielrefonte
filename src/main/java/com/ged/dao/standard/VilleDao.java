package com.ged.dao.standard;

import com.ged.entity.standard.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VilleDao extends JpaRepository<Ville, Long> {
//    @Query(value = "select c from Commune as c where c.libelleCommune like %:nom%")
//    Page<Commune> rechercherCommuneParNom(@Param("nom") String nom, PageRequest pageRequest);
    Optional<Ville> findByLibelleVilleIgnoreCase(String libelle);
}
