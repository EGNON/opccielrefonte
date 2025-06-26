package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.projection.SeanceOpcvmProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SeanceOpcvmDao extends JpaRepository<SeanceOpcvm, CleSeanceOpcvm> {
    @Query("select s from SeanceOpcvm s " +
           "where s.idSeanceOpcvm.idOpcvm=:idOpcvm " +
            "and s.estEnCours=true ")
    SeanceOpcvm afficherSeanceEnCours(long idOpcvm);
    @Query("select s from SeanceOpcvm s " +
           "where s.idSeanceOpcvm.idOpcvm=:idOpcvm " +
            "and s.idSeanceOpcvm.idSeance=:idSeance ")
    SeanceOpcvm afficherSeance(long idOpcvm,long idSeance);
    @Query(value = "select s from SeanceOpcvm s where s.idSeanceOpcvm.idOpcvm = :id and " +
            "s.supprimer = false order by s.idSeanceOpcvm.idSeance desc")
    Page<SeanceOpcvm> listeSeanceOpcvm(@Param("id") Long id, Pageable pageable);
    @Query(value = "select s from SeanceOpcvm s " +
            "where cast(s.dateOuverture as STRING ) like %:valeur% or " +
            "cast(s.dateFermeture as STRING ) like %:valeur% " +
            "or s.opcvm.denominationOpcvm like %:valeur% " +
            "or cast(s.navBenchmark as string ) like %:valeur% " +
            "order by s.idSeanceOpcvm.idSeance desc")
    Page<SeanceOpcvm> rechercher(String valeur, Pageable pageable);

    @Query(value = "select s from SeanceOpcvm s order by s.idSeanceOpcvm.idSeance desc")
    Page<SeanceOpcvm> listeSeanceOpcvm( Pageable pageable);
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

    @Query("update SeanceOpcvm s set s.navBenchmark=:navBenchmark" +
            " where s.idSeanceOpcvm.idOpcvm=:idOpcvm and " +
            "s.idSeanceOpcvm.idSeance=:idSeance")
    @Modifying
    int modifier(Long idOpcvm, Long idSeance, BigDecimal navBenchmark);
    @Query("update SeanceOpcvm s set s.niveau=:niveau" +
            " where s.idSeanceOpcvm.idOpcvm=:idOpcvm and " +
            "s.idSeanceOpcvm.idSeance=:idSeance and s.estEnCours=true and s.supprimer=false")
    @Modifying
    int modifier(Long idOpcvm, Long idSeance, Long niveau);
}
