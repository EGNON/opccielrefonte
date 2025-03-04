package com.ged.dao.opcciel.comptabilite;

import com.ged.dto.response.ConsultattionEcritureRes;
import com.ged.entity.opcciel.comptabilite.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OperationDao extends JpaRepository<Operation,Long> {
    @Query(value = "select new com.ged.dto.response.ConsultattionEcritureRes(op.idOperation, op.idActionnaire, o, op.actionnaire, op.idTransaction, op.transaction, op.idSeance, n, op.dateOperation, op.libelleOperation, op.dateValeur) " +
            "from Operation op inner join op.natureOperation n " +
            "inner join op.opcvm o where o.idOpcvm = :idOpcvm and (LTRIM(RTRIM(n.codeNatureOperation)) = LTRIM(RTRIM(:code)) or :code is null) " +
            "and cast(op.dateOperation as LocalDate) between cast(:dateDebut as LocalDate) and cast(:dateFin as LocalDate) " +
            "and op.supprimer = false and (op.idOperation = :idOperation or :idOperation is null) " +
            "and (op.idTransaction = :idTransaction or :idTransaction is null)")
    Page<ConsultattionEcritureRes> listeOperationsFiltree(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idOperation") Long idOperation,
            @Param("idTransaction") Long idTransaction,
            @Param("code") String code,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin,
            Pageable pageable
    );
}
