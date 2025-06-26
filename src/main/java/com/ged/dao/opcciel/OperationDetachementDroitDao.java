package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.OperationDetachementDroit;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationDetachementDroitDao extends JpaRepository<OperationDetachementDroit,Long>, JpaSpecificationExecutor<OperationDetachementDroit> {

}
