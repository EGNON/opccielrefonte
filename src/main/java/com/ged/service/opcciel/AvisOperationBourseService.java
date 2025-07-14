package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.AvisOperationBourse;
import com.ged.entity.opcciel.Ordre;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public interface AvisOperationBourseService {
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idOrdre,DatatableParameters parameters);
    AvisOperationBourse afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> verifierRegelementAttente(Long idOpcvm,LocalDateTime date,Long idOperationRL);
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idOrdre);
    ResponseEntity<Object> afficherReglementLivraison(Long idOpcvm);
    ResponseEntity<Object> afficherGenerationReglementLivraison(Long idOpcvm);
    ResponseEntity<Object> enregistrerReglementLivraison(String idOperation,String userLogin);
    ResponseEntity<Object> creer(AvisOperationBourseDto avisOperationBourseDto);
    ResponseEntity<Object> calculer(AvisOperationBourseDto avisOperationBourseDto);
    ResponseEntity<Object> modifier(AvisOperationBourseDto avisOperationBourseDto);
    ResponseEntity<Object> supprimer(Long idAvis,String userLogin);
}
