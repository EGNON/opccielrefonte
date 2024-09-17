package com.ged.dao.standard;

import com.ged.entity.standard.CleStatutPersonne;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.Qualite;
import com.ged.entity.standard.StatutPersonne;
import com.ged.projection.StatistiqueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatutPersonneDao extends JpaRepository<StatutPersonne, CleStatutPersonne> {
    @Query(value = "select sp from StatutPersonne as sp inner join Personne as p inner join Qualite as q " +
            "where q.libelleQualite like %:qualite%")
    StatutPersonne afficherStatutSelonQualite(@Param("qualite") String qualite);
    Boolean existsByPersonneAndQualite(Personne personne, Qualite qualite);
    Boolean existsByPersonne_IdPersonneAndQualite_IdQualite(Long idPersonne, Long idQualite);
    @Query(value = "select count(sp.personne.idPersonne) as nbre," +
            "q.libelleQualite as libelleQualite " +
            "from StatutPersonne as sp " +
            "inner join Qualite as q on q.idQualite = sp.qualite.idQualite " +
            "where (q.libelleQualite = :qualite or :qualite is null or :qualite = '') "+
            "group by q.libelleQualite")
    List<StatistiqueProjection> afficherNbrePersonneParQualite(@Param("qualite") Optional<String> qualite);
}
