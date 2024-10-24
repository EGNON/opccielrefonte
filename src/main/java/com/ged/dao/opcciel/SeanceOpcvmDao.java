package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeanceOpcvmDao extends JpaRepository<SeanceOpcvm, CleSeanceOpcvm> {
    @Query("select s from SeanceOpcvm s " +
           "where s.idSeanceOpcvm.idOpcvm=:idOpcvm " +
            "and s.estEnCours=true ")
    SeanceOpcvm afficherSeanceEnCours(long idOpcvm);
}
