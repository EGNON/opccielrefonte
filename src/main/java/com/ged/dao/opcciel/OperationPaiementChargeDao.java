package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationPaiementCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationPaiementChargeDao extends JpaRepository<OperationPaiementCharge, Long> {

}
