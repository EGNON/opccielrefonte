package com.ged.service.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.entity.standard.PersonneMorale;
import com.ged.projection.PersonnePhysiqueProjection;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonneMoraleService {
    DataTablesResponse<PersonneMoraleDto> afficherTous(String qualite, DatatableParameters parameters);
    DataTablesResponse<PersonneMoraleDto> afficherPersonneMoralePolitiquementExpose(String qualite, DatatableParameters parameters);
    DataTablesResponse<PersonneMoraleDto> afficherPersonneMoraleJuge(String qualite, DatatableParameters parameters);
    DataTablesResponse<PersonneMoraleDto> afficherPersonneExpose(DatatableParameters parameters);
    DataTablesResponse<PersonneMoraleDto> afficherPersonneJuge(DatatableParameters parameters);
    Long afficherMaxNumordre(String valeur);
    List<PersonneMoraleDto> afficherSelonQualiteEtat(String qualite, HttpServletResponse response) throws IOException, JRException;
    Page<PersonneMoraleDto> afficherPersonneMorales(int page, int size);
    PersonneMoraleDto afficherPersonneMorale(Long id);
    PersonneMorale afficherPersonneMoraleSelonId(Long idPersonne);
    PersonneMoraleDto creerPersonneMorale(PersonneMoraleDto personneMoraleDto);
    PersonneMoraleDto modifierPersonneMorale(PersonneMoraleDto personneMoraleDto);

    List<Object> createPMFromOppciel1() throws JsonProcessingException;

    void supprimerPersonneMorale(Long idPersonne);
    void supprimerPersonneMorale(Long idPersonne,Long idQualite);
    List<PersonneMoraleDto> afficherSelonQualite(String qualite);
    List<PersonneMoraleDto> afficherSelonQualiteLab(String qualite);
    List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvestiEtat(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin, HttpServletResponse response) throws IOException, JRException;
}
