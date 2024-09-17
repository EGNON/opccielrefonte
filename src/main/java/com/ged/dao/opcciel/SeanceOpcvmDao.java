package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceOpcvmDao extends JpaRepository<SeanceOpcvm, CleSeanceOpcvm> {

}
