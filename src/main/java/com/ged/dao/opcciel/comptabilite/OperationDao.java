package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OperationDao extends JpaRepository<Operation,Long> {
    @Query(value = "select op from Operation op join fetch op.natureOperation n " +
            "join fetch op.opcvm o where o.idOpcvm = :idOpcvm and op.dateOperation " +
            "between :dateDebut and :dateFin and op.supprimer = false and n.codeNatureOperation = :code")
    Page<Operation> listeOperationsFiltree(
            @Param("idOpcvm") Long idOpcvm,
            @Param("code") String code,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin,
            Pageable pageable);
}
