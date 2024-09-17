package com.ged.dao.standard;

import com.ged.entity.standard.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartementDao extends JpaRepository<Departement, Long> {
    Page<Departement> findByLibelleDepartementContains(String keyword, PageRequest pageRequest);
    @Query(value = "select d from Departement as d where d.libelleDepartement like %:nom%")
    Page<Departement> rechercherDepartementParNom(@Param("nom") String nom, PageRequest pageRequest);

    @Query(value = "select d from Departement as d inner join Commune as c on c.departement.idDepartement = d.idDepartement where c.libelleCommune like %:nom%")
    Departement rechercherDepartementParNomCommune(@Param("nom") String nom);
}
