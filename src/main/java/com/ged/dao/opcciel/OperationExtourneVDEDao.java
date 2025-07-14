package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleOperationExtourneVDE;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.OperationExtourneVDE;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationExtourneVDEDao extends JpaRepository<OperationExtourneVDE, CleOperationExtourneVDE>, JpaSpecificationExecutor<OperationExtourneVDE> {
    @Query(value = "select o from OperationExtourneVDE o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationExtourneVDE.idSeance=:idSeance and o.supprimer=false")
    Page<OperationExtourneVDE> afficherListe(Long idOpcvm,Long idSeance,Pageable pageable);
    @Query(value = "select o from OperationExtourneVDE o " +
            "where o.opcvm.idOpcvm=:idOpcvm and o.idOperationExtourneVDE.idSeance=:idSeance and o.supprimer=false")
    List<OperationExtourneVDE> afficherListe(Long idOpcvm,Long idSeance);

    @Query(value = "update  OperationExtourneVDE o " +
            "set o.dateVerification1=:dateVerification1,o.userLoginVerificateur1=:userLogin1,o.estVerifie1=:estVerifie1 " +
            "where o.idOperationExtourneVDE.idSeance=:idSeance and o.idOperationExtourneVDE.idOpcvm=:idOpcvm and " +
            "o.idOperationExtourneVDE.idTitre=:idTitre")
    @Modifying
    int modifierNiveau1(LocalDateTime dateVerification1,
                 String userLogin1, boolean estVerifie1,Long idSeance,Long idOpcvm,Long idTitre);
    @Query(value = "update  OperationExtourneVDE o " +
            "set o.dateVerification2=:dateVerification2,o.userLoginVerificateur2=:userLogin2,o.estVerifie2=:estVerifie2 " +
            "where o.idOperationExtourneVDE.idSeance=:idSeance and o.idOperationExtourneVDE.idOpcvm=:idOpcvm and " +
            "o.idOperationExtourneVDE.idTitre=:idTitre")
    @Modifying
    int modifierNiveau2(LocalDateTime dateVerification2,
                 String userLogin2, boolean estVerifie2,Long idSeance,Long idOpcvm,Long idTitre);
    List<OperationExtourneVDE> findByOpcvmAndSupprimer(Opcvm opcvm,Boolean supprimer);
}
