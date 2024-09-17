package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.TypeOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TypeOperationDao extends JpaRepository<TypeOperation,String> {
   Optional<TypeOperation> findByCodeTypeOperationIgnoreCase(String code);
   Optional<TypeOperation> findByLibelleTypeOperationIgnoreCase(String libelle);
   Page<TypeOperation> findBySupprimer(boolean supprimer, Pageable pageable);
   List<TypeOperation> findBySupprimerOrderByLibelleTypeOperationAsc(boolean supprimer);

   @Query(value = "select t from TypeOperation t " +
           "where (t.codeTypeOperation like %:valeur% " +
           "or t.libelleTypeOperation like %:valeur%) " +
           "and t.supprimer=false")
   Page<TypeOperation> rechercher(String valeur,Pageable pageable);
}
