package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationTransfertPart;
import com.ged.entity.standard.ParametreJourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParametreJourFerieDao extends JpaRepository<ParametreJourFerie, Long> {

}
