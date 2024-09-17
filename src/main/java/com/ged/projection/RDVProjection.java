package com.ged.projection;

import com.ged.dto.crm.AgentConcerneDto;
import com.ged.entity.crm.AgentConcerne;
import com.ged.entity.standard.*;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public interface RDVProjection {
    Quartier getQuartier();
    Commune getCommune();
    //    String getLibelleQuartier();
    Long getIdRdv();
    Personne getPersonne();
    ModeleMsgAlerte getModeleMsgAlerte();
    Date getDateDebRdv();
    Date getDateFinRdv();
    Time getHeureFinRdv();
    Time getHeureDebutRdv();

    Date getDateDebReelle();
    Date getDateFinReelle();
    Time getHeureFinReelle();
    Time getHeureDebReelle();

    String getObjetRdv();
    String getDenomination();
    Pays getPays();
    Set<AgentConcerne> getAgentConcernes();
//    String getLibelleFr();
//    String getLibelleVille();

}
