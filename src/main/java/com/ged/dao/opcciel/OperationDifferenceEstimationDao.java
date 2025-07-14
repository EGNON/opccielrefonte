package com.ged.dao.opcciel;

import com.ged.entity.opcciel.*;
import com.ged.projection.OperationDifferenceEstimationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationDifferenceEstimationDao extends JpaRepository<OperationDifferenceEstimation, CleOperationDifferenceEstimation>, JpaSpecificationExecutor<OperationExtourneVDE> {
    @Query(value = "select o from OperationDifferenceEstimation o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationDifferenceEstimation.idSeance=:idSeance and o.supprimer=false")
    Page<OperationDifferenceEstimation> afficherListe(Long idOpcvm, Long idSeance, Pageable pageable);

    @Query(value = "select o from OperationDifferenceEstimation o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationDifferenceEstimation.idSeance=:idSeance and o.supprimer=false")
    List<OperationDifferenceEstimation>  afficherListe(Long idOpcvm, Long idSeance);
    @Query(value = "select o from OperationDifferenceEstimation o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationDifferenceEstimation.idSeance=:idSeance and o.supprimer=false" +
            " and o.estVerifie1=:estVerifie1 and o.estVerifie2=:estVerifie2")
    List<OperationDifferenceEstimationProjection>  afficherListeJasper(Long idOpcvm, Long idSeance, Boolean estVerifie1, Boolean estVerifie2);

    @Query("update OperationDifferenceEstimation o set" +
            " o.estVerifie1=:estVerifie1,o.userLoginVerificateur1=:userLoginVerificateur1,o.dateVerification1=:dateVerification1" +
            " where o.idOperationDifferenceEstimation.idTitre=:idTitre and o.idOperationDifferenceEstimation.idSeance=:idSeance" +
            " and o.idOperationDifferenceEstimation.idOpcvm=:idOpcvm")
    @Modifying
    int modifier1(Long idOpcvm, Long idTitre, Long idSeance, String userLoginVerificateur1, LocalDateTime dateVerification1,
                 Boolean estVerifie1);
    @Query("update OperationDifferenceEstimation o set" +
            " o.estVerifie2=:estVerifie2,o.userLoginVerificateur2=:userLoginVerificateur2,o.dateVerification2=:dateVerification2" +
            " where o.idOperationDifferenceEstimation.idTitre=:idTitre and o.idOperationDifferenceEstimation.idSeance=:idSeance" +
            " and o.idOperationDifferenceEstimation.idOpcvm=:idOpcvm")
    @Modifying
    int modifier2(Long idOpcvm, Long idTitre, Long idSeance, String userLoginVerificateur2, LocalDateTime dateVerification2,
                 Boolean estVerifie2);
}
