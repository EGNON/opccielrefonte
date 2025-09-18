package com.ged.service.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonnePhysiqueDto;
import com.ged.dto.standard.PersonnePhysiqueDtoEJ;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.projection.FicheKYCProjection;
import com.ged.projection.NumOrdreProjection;
import com.ged.projection.PersonnePhysiqueProjection;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonnePhysiqueService{
    ResponseEntity<Object> afficherTous();

    ResponseEntity<Object> afficherTous(DatatableParameters parameters);

    DataTablesResponse<PersonnePhysiqueDto> afficherTous(String qualite, DatatableParameters parameters);
    DataTablesResponse<PersonnePhysiqueDto> afficherPersonneExpose(DatatableParameters parameters);
    DataTablesResponse<PersonnePhysiqueDto> afficherPersonneJuge(DatatableParameters parameters);
    DataTablesResponse<PersonnePhysiqueDto> afficherPersonnePhysiquePolitiquementExpose(String qualite, DatatableParameters parameters);
    DataTablesResponse<PersonnePhysiqueDto> afficherPersonnePhysiqueJuge(String qualite, DatatableParameters parameters);
    Page<PersonnePhysiqueDto> afficherPersonnePhysiques(int page, int size);
    PersonnePhysiqueDto afficherPersonnePhysique(Long id);
    PersonnePhysiqueDto afficherSelonIdQualite(Long id, String qualite);
    Long afficherMaxNumordre();
    List<PersonnePhysiqueDto> afficherSelonQualite(String qualite);
    List<PersonnePhysiqueDto> afficherSelonQualiteLab(String qualite);
    List<PersonnePhysiqueProjection> afficherSelonQualiteEtat(String qualite, HttpServletResponse response) throws IOException, JRException;
    List<PersonnePhysiqueDto> afficherPersonnePhysiqueNayantPasInvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin);
    List<PersonnePhysiqueProjection> afficherPersonnePhysiqueNayantPasInvestiEtat(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin, HttpServletResponse response) throws IOException, JRException;
    PersonnePhysique afficherPersonnePhysiqueSelonId(long idPersonne);
    List<FicheKYCProjection> afficherFicheKYC(long idPersonne, HttpServletResponse response) throws IOException, JRException;
    PersonnePhysiqueDto creerPersonnePhysique(List<MultipartFile> files, PersonnePhysiqueDto personnePhysiqueDto);
    PersonnePhysiqueDto modifierPersonnePhysique(List<MultipartFile> files, PersonnePhysiqueDto personnePhysiqueDto) throws Throwable;
    PersonnePhysiqueDto modifierPersonnePhysiqueLab(List<MultipartFile> files, PersonnePhysiqueDto personnePhysiqueDto) throws Throwable;

    List<Object> createPHFromOppciel1() throws JsonProcessingException;

    PersonnePhysiqueDtoEJ creerPersonnePhysiqueEJ(List<MultipartFile> files, PersonnePhysiqueDtoEJ personnePhysiqueDtoEJ);
    PersonnePhysiqueDtoEJ modifierPersonnePhysiqueEJ(List<MultipartFile> files, PersonnePhysiqueDtoEJ personnePhysiqueDtoEJ) throws Throwable;

    void supprimerPersonnePhysique(long idPersonne);
    void supprimerPersonnePhysique(long idPersonne,long idQualite);
}
