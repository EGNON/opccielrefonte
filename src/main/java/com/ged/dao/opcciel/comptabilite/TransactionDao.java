package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TransactionDao extends JpaRepository<Transaction,Long> {
    @Query(value = "select t.idTransaction from Transaction t inner join t.natureOperation n inner join t.opcvm o where o.idOpcvm = :idOpcvm and trim(n.codeNatureOperation) = trim(:code) " +
            "and day(t.dateTransaction) = day(:date) and month(t.dateTransaction) = month(:date) and year(t.dateTransaction) = year(:date)")
    Long getIdTransactionByCodeAndDate(
            @Param("idOpcvm") Long idOpcvm,
            @Param("code") String code,
            @Param("date") LocalDateTime date);
}
