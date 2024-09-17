package com.ged.dao.crm;

import com.ged.entity.crm.AgentConcerne;
import com.ged.entity.crm.CleAgentConcerne;
import com.ged.entity.standard.Personnel;
import com.ged.entity.crm.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentConcerneDao extends JpaRepository<AgentConcerne, CleAgentConcerne> {
    List<AgentConcerne> findByRdv(RDV rdv);
    List<AgentConcerne> findByPersonnel(Personnel personnel);
    void deleteByRdv(RDV rdv);
    @Query(value = "update AgentConcerne set "+
            "etat=:mEtat " +
            "where id.idRdv=:mIdRdv "+
            "and id.idPersonne=:mIdPersonne")
    @Modifying
    int updateAgentConcerne(long mIdRdv,long mIdPersonne, boolean mEtat);
}
