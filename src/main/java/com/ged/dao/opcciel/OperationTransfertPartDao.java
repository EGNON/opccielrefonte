package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationTransfertPart;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationTransfertPartDao extends JpaRepository<OperationTransfertPart, Long> {
    @Query(value = "select op from OperationTransfertPart op " +
            "inner join OperationTransfertPart op1 on op1.idOpDepart = op.idOperation " +
            "where op.opcvm.idOpcvm = :idOpcvm")
    List<OperationTransfertPart> afficherTousTransfertDepart(
            @Param("idOpcvm") Long idOpcvm
    );
}
