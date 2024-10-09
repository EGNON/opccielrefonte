package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ActionnaireOpcvm;
import com.ged.entity.standard.CleActionnaireOpcvm;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionnaireOpcvmDao extends JpaRepository<ActionnaireOpcvm,CleActionnaireOpcvm> {
    @Query("select a from ActionnaireOpcvm a " +
            "where a.opcvm.idOpcvm =:idOpcvm " +
            "order by a.personne.denomination asc")
    Page<ActionnaireOpcvm> afficherParOpcvm(long idOpcvm,Pageable pageable);

    @Query("select a from ActionnaireOpcvm a " +
            "where a.opcvm.idOpcvm =:idOpcvm and a.personne.denomination like %:valeur% " +
            "order by a.personne.denomination asc")
    Page<ActionnaireOpcvm> rechercher(long idOpcvm,String valeur,Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "insert into Tarification.TJ_OpcvmActionnaire (idPersonne, idOpcvm,supprimer) " +
            "VALUES (?, ?,?)", nativeQuery = true)
    int enregistrer(Long idPersonne, Long idOpcvm,boolean supprimer);

}
