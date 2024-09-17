package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ActionnaireCommission;
import com.ged.entity.standard.CleActionnaireCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionnaireCommissionDao extends JpaRepository<ActionnaireCommission,CleActionnaireCommission> {
    Page<ActionnaireCommission> findByOpcvm(Opcvm opcvm, Pageable pageable);
}
