package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepotRachatDao extends JpaRepository<DepotRachat,Long> {
    @Query(value = "select d from DepotRachat d join d.natureOperation n where n.codeNatureOperation = 'DEP_SOUS'")
    Page<DepotRachat> listeDesDepotSeance(Pageable pageable);
    List<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperationAndEstVerifierAndEstVerifie1AndEstVerifie2AndSupprimer
            (Opcvm opcvm, long idSeance, NatureOperation natureOperation,
             boolean estVerifier,boolean estVerifie1,boolean estVerifie2,boolean supprimer);
    @Query(value = "select d from DepotRachat d join d.natureOperation n where trim(n.codeNatureOperation) = 'DEP_SOUS' " +
            "and d.opcvm.idOpcvm = :idOpcvm and d.idSeance = :idSeance and d.supprimer = false order by d.idDepotRachat desc")
    Page<DepotRachat> listeDesDepotSeance(
            @Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance, Pageable pageable);
    @Query(value = "select d from DepotRachat d join d.natureOperation n join d.actionnaire join d.personne " +
            "where trim(n.codeNatureOperation) = 'DEP_SOUS' " +
            "and d.opcvm.idOpcvm = :idOpcvm and d.idSeance = :idSeance " +
            "and d.estVerifie1 = false and d.estVerifie2 = false order by d.idDepotRachat desc")
    List<DepotRachat> listeVerifDepotSeance(@Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance);
    @Query(value = "select d from DepotRachat d join d.natureOperation n join d.actionnaire join d.personne " +
            "where trim(n.codeNatureOperation) = 'DEP_SOUS' " +
            "and d.opcvm.idOpcvm = :idOpcvm and d.idSeance = :idSeance and (d.estVerifier = :estVerifier or :estVerifier is null) " +
            "and (d.estVerifie1 = :estVerifie1 or :estVerifie1 is null) and " +
            "(:estVerifie2 is null or d.estVerifie2 = :estVerifie2) order by d.idDepotRachat desc")
    Page<DepotRachat> tousLesDepotsSouscription(
            @Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance,
            @Param("estVerifier") Boolean estVerifier, @Param("estVerifie1") Boolean estVerifie1,
            @Param("estVerifie2") Boolean estVerifie2, Pageable pageable);
    @Query(value = "select d from DepotRachat d join d.natureOperation n join d.actionnaire join d.personne " +
            "where trim(n.codeNatureOperation) = 'DEP_SOUS' " +
            "and d.opcvm.idOpcvm = :idOpcvm and d.idSeance = :idSeance and (d.estVerifier = :estVerifier or :estVerifier is null) " +
            "and (d.estVerifie1 = :estVerifie1 or :estVerifie1 is null) and " +
            "(:estVerifie2 is null or d.estVerifie2 = :estVerifie2) order by d.idDepotRachat desc")
    List<DepotRachat> tousLesDepotsSouscription(
            @Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance,
            @Param("estVerifier") Boolean estVerifier, @Param("estVerifie1") Boolean estVerifie1,
            @Param("estVerifie2") Boolean estVerifie2);
    Page<DepotRachat> findByOpcvmAndIdSeanceAndNatureOperation(Opcvm opcvm, long idSeance, NatureOperation natureOperation, Pageable pageable);
    @Query(value = "select d from DepotRachat d inner join d.actionnaire a inner join d.natureOperation n " +
            "inner join d.opcvm o where d.supprimer = false and d.idSeance = :idSeance " +
            "and o.idOpcvm = :idOpcvm and a.idPersonne = :idPersonne and n.codeNatureOperation in (:codeNatureOpList)")
    List<DepotRachat> getAllDepotSouscToValidate(
            @Param("idSeance") Long idSeance,
            @Param("idOpcvm") Long idOpcvm,
            @Param("idPersonne") Long idPersonne,
            List<String> codeNatureOpList
    );

}
