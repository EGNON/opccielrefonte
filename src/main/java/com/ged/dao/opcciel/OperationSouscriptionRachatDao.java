package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationSouscriptionRachatDao extends JpaRepository<OperationSouscriptionRachat,Long> {
    Page<OperationSouscriptionRachat> findBySupprimer(boolean valeur,Pageable pageable);
    List<OperationSouscriptionRachat> findBySupprimer(boolean valeur);
}
