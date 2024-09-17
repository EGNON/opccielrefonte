package com.ged.dao.crm;

import com.ged.entity.crm.Degre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DegreDao extends JpaRepository<Degre, Long> {
    @Query(value = "select d from Degre as d where d.idDegre = :idDegre")
    Degre retrouverDregreSelonId(@Param("idDegre") Long idDegre);
    @Query(value = "select d from Degre as d where d.libelle like %:nom%")
    Page<Degre> rechercherDegresParNom(@Param("nom") String nom, PageRequest pageRequest);
    Degre findByLibelle(String libelle);
    Page<Degre> findByLibelleContainsIgnoreCase(String libelle, Pageable pageable);
}
