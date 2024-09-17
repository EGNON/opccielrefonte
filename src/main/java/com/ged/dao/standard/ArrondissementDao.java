package com.ged.dao.standard;

import com.ged.entity.standard.Arrondissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArrondissementDao extends JpaRepository<Arrondissement, Long> {
    @Query(value = "select a from Arrondissement as a where a.libelleArrondissement like %:nom%")
    Page<Arrondissement> rechercherArrondissementParNom(@Param("nom") String nom, PageRequest pageRequest);
}
