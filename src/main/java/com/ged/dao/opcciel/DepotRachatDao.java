package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepotRachatDao extends JpaRepository<DepotRachat,Long> {
    @Query(value = "select d from DepotRachat d join d.natureOperation n where n.codeNatureOperation = 'DEP_SOUS'")
    Page<DepotRachat> listeDesDepotSeance(Pageable pageable);
    Page<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperation(Opcvm opcvm, long idSeance, NatureOperation natureOperation, Pageable pageable);
    List<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperationAndEstVerifierAndEstVerifie1AndEstVerifie2AndSupprimer
            (Opcvm opcvm, long idSeance, NatureOperation natureOperation,
             boolean estVerifier,boolean estVerifie1,boolean estVerifie2,boolean supprimer);
}
