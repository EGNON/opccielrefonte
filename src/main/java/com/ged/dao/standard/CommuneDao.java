package com.ged.dao.standard;

import com.ged.entity.standard.Commune;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommuneDao extends JpaRepository<Commune, Long> {
    @Query(value = "select c from Commune as c where c.libelleCommune like %:nom%")
    Page<Commune> rechercherCommuneParNom(@Param("nom") String nom, PageRequest pageRequest);
}
