package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.CleOperationFormule;
import com.ged.entity.opcciel.comptabilite.OperationFormule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationFormuleDao extends JpaRepository<OperationFormule, CleOperationFormule> {
    /*@Modifying
    @Query(value = "INSERT INTO Comptabilite.TJ_OperationFormule VALUES (:#{#me.id}, :#{#me.value})", nativeQuery = true)
    void save(@Param("op") OperationFormule op);*/
}
