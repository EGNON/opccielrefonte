package com.ged.service.crm;

import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.dto.crm.ObjectifAffecteEtatDto;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.crm.ObjectifAffecte;
import com.ged.projection.ObjectifAffecteProjection;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public interface ObjectifAffecteService {
    Page<ObjectifAffecteDto> afficherObjectifAffectes(int page, int size);
    List<ObjectifAffecteEtatDto> afficherSelonPersonnelEtPeriodicite(long idPersonne, BeginEndDateParameter beginEndDateParameter);
    List<ObjectifAffecteProjection> afficherSelonPersonnelEtPeriodiciteEtat(String etat,long idPersonne,BeginEndDateParameter beginEndDateParameter, HttpServletResponse response) throws IOException, JRException;
    ObjectifAffecte afficherObjectifAffecteSelonId(CleObjectifAffecte idObjectifAffecte);
    ObjectifAffecteDto creerObjectifAffecte(ObjectifAffecteDto objectifAffecteDto);
    ObjectifAffecteDto modifierObjectifAffecte(ObjectifAffecteDto objectifAffecteDto);
    void supprimerObjectifAffecte(CleObjectifAffecte idObjectifAffecte);
    void supprimerSelonIdAffectation(Long id);
}
