package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepotRachatDao extends JpaRepository<DepotRachat,Long> {
    @Query(value = "select d from DepotRachat d join d.natureOperation n where n.codeNatureOperation = 'DEP_SOUS' " +
            "and d.opcvm.idOpcvm = :idOpcvm and d.idSeance = :idSeance order by d.idOperation desc")
    Page<DepotRachat> listeDesDepotSeance(
            @Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance, Pageable pageable);
    Page<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperation(
            Opcvm opcvm, long idSeance, NatureOperation natureOperation, Pageable pageable);
}
