package com.ged.dao.opcciel.comptabilite;

import com.ged.dto.response.ConsultattionEcritureRes;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.projection.ConsultattionEcritureResProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationDao extends JpaRepository<Operation,Long> {
    @Query(value = "select new com.ged.dto.response.ConsultattionEcritureRes(op.idOperation, op.idActionnaire, o, op.actionnaire, op.idTransaction, op.transaction, op.idSeance, n, op.dateOperation, op.libelleOperation, op.dateValeur) " +
            "from Operation op inner join op.natureOperation n " +
            "inner join op.opcvm o where o.idOpcvm = :idOpcvm and (COALESCE(:code, null) is null or LTRIM(RTRIM(n.codeNatureOperation)) = LTRIM(RTRIM(:code))) " +
//            "and cast(op.dateOperation as LocalDate) between cast(:dateDebut as LocalDate) and cast(:dateFin as LocalDate) " +
            "and op.supprimer = false and (COALESCE(:idOperation, null) is null or op.idOperation = :idOperation) " +
            "and (COALESCE(:idTransaction, null) is null or op.idTransaction = :idTransaction) order by op.idOperation desc ")
    Page<ConsultattionEcritureRes> listeOperationsFiltree(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idOperation") Long idOperation,
            @Param("idTransaction") Long idTransaction,
            @Param("code") String code,
//            @Param("dateDebut") LocalDateTime dateDebut,
//            @Param("dateFin") LocalDateTime dateFin,
            Pageable pageable
    );
    @Query(value = "select op " +
            "from Operation op " +
            "inner join Opcvm o on op.opcvm.idOpcvm=o.idOpcvm" +
            " where o.idOpcvm = :idOpcvm and op.natureOperation.codeNatureOperation='RETROCOMINVEST' " +
            "and o.supprimer=false " +
            " order by op.idOperation desc ")
    Page<Operation> afficherPaiementCommissionInvestissement(
            @Param("idOpcvm") Long idOpcvm,
            Pageable pageable
    );
    @Query("update  Operation o " +
            "set o.montant=:montant," +
            "o.valeurFormule=:valeurFormule," +
            "o.valeurCodeAnalytique=:valeurCodeAnalytique " +
            "where o.idOperation=:idOperation")
    @Modifying()
    int modifier(Long idOperation, String valeurFormule, String valeurCodeAnalytique, BigDecimal montant);
    @Query("update Operation o set " +
            "o.userLoginVerificateur1=:userLoginVerificateur1," +
            "o.userLoginVerificateur2=:userLoginVerificateur2," +
            "o.estVerifie1=:estVerifie1,o.estVerifie2=:estVerifie2," +
            "o.dateVerification1=:dateVerification1,o.dateVerification2=:dateVerification2 " +
            "where o.idOperation=:idOperation")
    @Modifying()
    int modifier(String userLoginVerificateur1,String userLoginVerificateur2, Boolean estVerifie1,
                 Boolean estVerifie2,LocalDateTime dateVerification1,LocalDateTime dateVerification2,
                 Long idOperation);
    @Query("update Operation o set " +
            "o.userLoginVerificateur2=:userLoginVerificateur2," +
            "o.estVerifie2=:estVerifie2," +
            "o.dateVerification2=:dateVerification2 " +
            "where o.idOperation=:idOperation")
    @Modifying()
    int modifier(String userLoginVerificateur2,
                 Boolean estVerifie2,LocalDateTime dateVerification2,
                 Long idOperation);
//    @Query(value = "select op.idOperation, op.idActionnaire, o, op.actionnaire, op.idTransaction, op.transaction, op.idSeance, n, op.dateOperation, op.libelleOperation, op.dateValeur " +
//            "from Operation op left outer join  NatureOperation n on op.natureOperation.codeNatureOperation=n.codeNatureOperation " +
//            "inner join Opcvm o on op.opcvm.idOpcvm =o.idOpcvm " +
//            "where o.idOpcvm = :idOpcvm and (COALESCE(:code, null) is null or LTRIM(RTRIM(n.codeNatureOperation)) = LTRIM(RTRIM(:code))) " +
//            "and op.dateOperation between :dateDebut  and :dateFin " +
//            "and op.supprimer = false and (COALESCE(:idOperation, null) is null or op.idOperation = :idOperation) " +
//            "and (COALESCE(:idTransaction, null) is null or op.idTransaction = :idTransaction) order by op.idOperation desc ")
//    Page<ConsultattionEcritureResProjection> listeOperationsFiltree(
//            @Param("idOpcvm") Long idOpcvm,
//            @Param("idOperation") Long idOperation,
//            @Param("idTransaction") Long idTransaction,
//            @Param("code") String code,
//            @Param("dateDebut") LocalDateTime dateDebut,
//            @Param("dateFin") LocalDateTime dateFin,
//            Pageable pageable
//    );
//    @Query(value = "select op.idOperation, op.idActionnaire, o, op.actionnaire, op.idTransaction, op.transaction, op.idSeance, n, op.dateOperation, op.libelleOperation, op.dateValeur " +
//            "from Operation op left outer join  NatureOperation n on op.natureOperation.codeNatureOperation=n.codeNatureOperation " +
//            "inner join Opcvm o on op.opcvm.idOpcvm =o.idOpcvm " +
//            "where o.idOpcvm = :idOpcvm and (COALESCE(:code, null) is null or LTRIM(RTRIM(n.codeNatureOperation)) = LTRIM(RTRIM(:code))) " +
////            "and op.dateOperation between :dateDebut  and :dateFin " +
//            "and op.supprimer = false and (COALESCE(:idOperation, null) is null or op.idOperation = :idOperation) " +
//            "and (COALESCE(:idTransaction, null) is null or op.idTransaction = :idTransaction) order by op.idOperation desc ")
//    Page<ConsultattionEcritureResProjection> listeOperationsFiltree(
//            @Param("idOpcvm") Long idOpcvm,
//            @Param("idOperation") Long idOperation,
//            @Param("idTransaction") Long idTransaction,
//            @Param("code") String code,
//            Pageable pageable
//    );

    /*@Query(value = "select new com.ged.dto.response.ConsultattionEcritureRes(op.idOperation, op.idActionnaire, op.opcvm, op.actionnaire, op.idTransaction, op.transaction, op.idSeance, op.natureOperation, op.dateOperation, op.libelleOperation, op.dateValeur) " +
            "from Operation op order by op.idOperation desc")
    Page<ConsultattionEcritureRes> listeOperationsFiltree(
            *//*@Param("idOpcvm") Long idOpcvm,
            @Param("idOperation") Long idOperation,
            @Param("idTransaction") Long idTransaction,
            @Param("code") String code,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin,*//*
            Pageable pageable
    );*/
}
