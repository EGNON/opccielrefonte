package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.projection.SeanceOpcvmProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeanceOpcvmDao extends JpaRepository<SeanceOpcvm, CleSeanceOpcvm> {
    @Query("select s from SeanceOpcvm s " +
           "where s.idSeanceOpcvm.idOpcvm=:idOpcvm " +
            "and s.estEnCours=true ")
    SeanceOpcvm afficherSeanceEnCours(long idOpcvm);
    @Query(value = "select s from SeanceOpcvm s where s.idSeanceOpcvm.idOpcvm = :id and " +
            "s.supprimer = false order by s.idSeanceOpcvm.idSeance desc")
    Page<SeanceOpcvm> listeSeanceOpcvm(@Param("id") Long id, Pageable pageable);
    @Query(value = "select s.opcvm.idOpcvm as idOpcvm," +
            "s.opcvm.sigleOpcvm as sigleOpcvm," +
            "s.opcvm.denominationOpcvm as denominationOpcvm," +
            "s.idSeanceOpcvm.idSeance as idSeance," +
            "concat(s.idSeanceOpcvm.idSeance,'-('" +
            ",cast(s.dateOuverture as date) ,')-','('," +
            "cast(s.dateFermeture as date ), ')') as libelleSeance " +
            "from SeanceOpcvm s " +
            "where s.opcvm.idOpcvm=:idOpcvm" +
            " order by s.idSeanceOpcvm.idSeance desc")
    List<SeanceOpcvmProjection> listeSeanceOpcvmDesc(Long idOpcvm);
}
