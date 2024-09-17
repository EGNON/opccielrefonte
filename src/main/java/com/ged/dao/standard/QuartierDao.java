package com.ged.dao.standard;

import com.ged.entity.standard.Quartier;
import com.ged.projection.QuartierProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuartierDao extends JpaRepository<Quartier,Long> {
    @Query(value = "select q.idQuartier as idQuartier,"+
            "q.libelleQuartier as libelleQuartier "+
            "from Quartier as q " +
            "inner join Arrondissement a on q.arrondissement.idArrondissement=a.idArrondissement "+
            "inner join Ville v on v.idVille=a.ville.idVille "+
            "where v.idVille=:id")
    List<QuartierProjection> afficherQuartierSelonVille (@Param("id") long id);

    @Query(value = "select q.idQuartier as idQuartier,"+
            "q.libelleQuartier as libelleQuartier "+
            "from Quartier as q " +
            "inner join Arrondissement a on q.arrondissement.idArrondissement=a.idArrondissement "+
            "where a.commune.libelleCommune=:libelle " +
            "order by q.libelleQuartier asc")
    List<QuartierProjection> afficherQuartierSelonCommune (@Param("libelle") String libelle);
}
