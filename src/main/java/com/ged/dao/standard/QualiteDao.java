package com.ged.dao.standard;

import com.ged.entity.standard.Qualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QualiteDao extends JpaRepository<Qualite,Long> {
    Qualite findByLibelleQualiteContains(String libelleQualite);
    Optional<Qualite> findByLibelleQualiteIgnoreCase(String libelleQualite);
    Optional<Boolean> existsByLibelleQualiteIgnoreCase(String libelle);
    List<Qualite> findAllByEstPHIsOrderByLibelleQualiteAsc(Boolean estPH);
    List<Qualite> findAllByEstPMIsOrderByLibelleQualiteAsc(Boolean estPM);
    @Query(value = "SELECT q FROM Qualite q WHERE q.estPH = true AND LOWER(q.libelleQualite) NOT LIKE '%prospect%' AND q.idQualite NOT IN " +
            "(SELECT DISTINCT s.idStatutPersonne.idQualite FROM StatutPersonne s WHERE s.idStatutPersonne.idPersonne = :idPersonne) " +
            "ORDER BY q.libelleQualite ASC")
    List<Qualite> findAllByEstPhExceptSomeQualities(@Param("idPersonne") Long idPersonne);
    @Query(value = "SELECT q FROM Qualite q WHERE q.estPM = true AND LOWER(q.libelleQualite) NOT LIKE '%prospect%' AND q.idQualite NOT IN " +
            "(SELECT DISTINCT s.idStatutPersonne.idQualite FROM StatutPersonne s WHERE s.idStatutPersonne.idPersonne = :idPersonne) " +
            "ORDER BY q.libelleQualite ASC")
    List<Qualite> findAllByEstPmExceptSomeQualities(@Param("idPersonne") Long idPersonne);
}
