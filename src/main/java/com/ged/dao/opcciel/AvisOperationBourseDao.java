package com.ged.dao.opcciel;

import com.ged.entity.opcciel.AvisOperationBourse;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.projection.AvisOperationBourseProjection;
import com.ged.projection.OrdreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AvisOperationBourseDao extends JpaRepository<AvisOperationBourse, Long> {
    @Query(value = "select a from AvisOperationBourse a " +
            "where a.ordre.opcvm.idOpcvm=:idOpcvm and a.ordre.idOrdre=:idOrdre")
    Page<AvisOperationBourse> afficherAvisOperationBourse(Long idOpcvm,Long idOrdre,Pageable pageable);
    @Query(value = "select a from AvisOperationBourse a " +
            "where a.ordre.opcvm.idOpcvm=:idOpcvm and a.idOperationRL=0 " +
            "and a.supprimer=false")
    List<AvisOperationBourse> afficherReglementLivraison(Long idOpcvm);
    @Query(value = "select a from AvisOperationBourse a " +
                "where a.ordre.opcvm.idOpcvm=:idOpcvm and a.idOperationRL=0 " +
                "and a.supprimer=false and cast(a.dateReceptionLivraisonPrevu as string ) like %:dateFermeture%")
    List<AvisOperationBourse> afficherReglementLivraison(Long idOpcvm, String dateFermeture);

    @Query(value = "select a from AvisOperationBourse a " +
            "where a.ordre.opcvm.idOpcvm=:idOpcvm and a.ordre.idOrdre=:idOrdre")
    List<AvisOperationBourse> afficherAvisOperationBourse(Long idOpcvm,Long idOrdre);
}
