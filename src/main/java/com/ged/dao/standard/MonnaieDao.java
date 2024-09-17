package com.ged.dao.standard;

import com.ged.entity.standard.Monnaie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonnaieDao extends JpaRepository<Monnaie, String> {
    @Query(value = "select m from Monnaie as m where m.codeMonnaie = :code")
    Page<Monnaie> chercherMonnaireParCode(@Param("code") String code, PageRequest pageRequest);

    Monnaie findByCodeMonnaie(String codeMonnaie);
    Page<Monnaie> findByCodeMonnaieContainsIgnoreCaseOrNomContainsIgnoreCase(String code, String nom, Pageable pageable);
}
