package com.ged.dao.lab;

import com.ged.entity.standard.Personne;
import com.ged.entity.lab.GelDegel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface GelDegelDao extends JpaRepository<GelDegel,Long> {
    Page<GelDegel> findByEstGele(boolean estGele, Pageable pageable);
    GelDegel findByEstGeleAndPersonne(boolean estGele, Personne personne);

    @Query(value = "update GelDegel g set "+
            "g.dateFin=:mDateFin," +
            "g.estGele=false " +
            "where g.personne.idPersonne=:mIdPersonne and g.estGele=true")
    @Modifying
    int updateGelDegel(long mIdPersonne, LocalDateTime mDateFin);
}
