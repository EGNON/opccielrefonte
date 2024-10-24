package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRachatDao extends JpaRepository<DepotRachat,Long> {
    Page<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperation(Opcvm opcvm,
                                                               long idSeance,
                                                               NatureOperation natureOperation,
                                                               Pageable pageable);
}
