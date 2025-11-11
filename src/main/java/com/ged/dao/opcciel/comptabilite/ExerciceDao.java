package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.projection.ExerciceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciceDao extends JpaRepository<Exercice, CleExercice> {
    @Query("select distinct e.codeExercice as codeExercice "+
            "from Exercice e")
    List<ExerciceProjection> afficherExercice();
    @Query(value = "select e from Exercice e where e.estCourant = true and e.idExercie.idOpcvm = :id")
    Exercice exerciceCourant(@Param("id") Long idOpcvm);

    @Query(value = "select e from Exercice e where  e.idExercie.idOpcvm = :id")
    List<Exercice> exerciceOpcvm(@Param("id") Long idOpcvm);
    @Query(value = "select e from Exercice e where  e.idExercie.idOpcvm = :idOpcvm and e.codeExercice=:code")
    Exercice exerciceOpcvmCode( Long idOpcvm,String code);
    @Query(value = "select e from Exercice e " +
            "where  e.idExercie.idOpcvm = :idOpcvm " +
            "and supprimer=false " +
            "")
    Page<Exercice> exerciceOpcvm(Long idOpcvm, Pageable pageable);
    @Query(value = "select e from Exercice e where  e.idExercie.idOpcvm = :idOpcvm and supprimer=false" +
            " and codeExercice like %:valeur% ")
    Page<Exercice> exerciceOpcvm(String valeur,Long idOpcvm, Pageable pageable);

    @Query(value = "select e.codeExercice as codeExercice," +
            "e.opcvm as opcvm," +
            "e.dateDebut as dateDebut," +
            "e.dateFin as dateFin," +
            "e.plan as plan " +
            "from Exercice e " +
            "where e.estCourant = true " +
            "and e.idExercie.idOpcvm = :id")
    ExerciceProjection exerciceEnCours(@Param("id") Long idOpcvm);
    @Query(value="update Exercice set supprimer=true " +
            "where opcvm.idOpcvm=:idOpcvm and codeExercice=:codeExercice")
    @Modifying()
    int supprimer(Long idOpcvm,String codeExercice);
}
