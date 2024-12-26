package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationRestitutionReliquat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRestitutionReliquatDao extends JpaRepository<OperationRestitutionReliquat, Long> {
    @Query(value = "select op from OperationRestitutionReliquat op join op.opcvm o " +
            "join op.natureOperation n join op.actionnaire a " +
            "where op.supprimer = false and o.idOpcvm = :idOpcvm and op.idSeance = :idSeance order by op.idOperation desc")
    Page<OperationRestitutionReliquat> listeOpRestitutionReliquat(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idSeance") Long idSeance, Pageable pageable);
    @Query(value = "select op from OperationRestitutionReliquat op join op.opcvm o " +
            "join op.natureOperation n join op.actionnaire a " +
            "where op.supprimer = false and o.idOpcvm = :idOpcvm and op.idSeance = :idSeance order by op.idOperation desc")
    List<OperationRestitutionReliquat> listeOpRestitutionReliquat(@Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance);
}
