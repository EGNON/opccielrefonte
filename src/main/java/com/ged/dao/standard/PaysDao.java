package com.ged.dao.standard;

import com.ged.entity.standard.Pays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaysDao extends JpaRepository<Pays,Long> {
    Optional<Pays> findByLibelleFrIgnoreCase(String libelleFr);
    Boolean existsByLibelleFr(String libelleFr);
    Boolean existsByCodePaysIgnoreCase(String codePays);
    Boolean existsByLibelleFrIgnoreCase(String libelle);
    Optional<Pays> findByCodePaysIgnoreCase(String codePays);
    Page<Pays> findByEstGafi(boolean estGafi, Pageable pageable);
    List<Pays> findByEstGafiOrderByLibelleFrAsc(boolean estGafi);
    @Query(value = "select p from Pays as p " +
                    "where p.libelleFr like %:search% " +
                    "or p.libelleEn like %:search% " +
                    "or p.monnaie.codeMonnaie like %:search% "+
                    "or p.monnaie.nom like %:search% ")
    Page<Pays> rechercherPays(String search, Pageable pageable);
}
