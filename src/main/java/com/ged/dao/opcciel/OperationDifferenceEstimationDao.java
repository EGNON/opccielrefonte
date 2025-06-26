package com.ged.dao.opcciel;

import com.ged.entity.opcciel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationDifferenceEstimationDao extends JpaRepository<OperationDifferenceEstimation, CleOperationDifferenceEstimation>, JpaSpecificationExecutor<OperationExtourneVDE> {
    @Query(value = "select o from OperationDifferenceEstimation o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationDifferenceEstimation.idSeance=:idSeance and o.supprimer=false")
    Page<OperationDifferenceEstimation> afficherListe(Long idOpcvm, Long idSeance, Pageable pageable);

    @Query(value = "select o from OperationDifferenceEstimation o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationDifferenceEstimation.idSeance=:idSeance and o.supprimer=false")
    List<OperationDifferenceEstimation>  afficherListe(Long idOpcvm, Long idSeance);

}
