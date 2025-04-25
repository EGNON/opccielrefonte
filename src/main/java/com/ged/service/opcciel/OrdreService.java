package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.Ordre;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OrdreService {
    ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters);
    Ordre afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTous(Long idOpcvm);
    ResponseEntity<Object> creer(OrdreDto OrdreDto);
    ResponseEntity<Object> calculer(OrdreDto ordreDto);

    ResponseEntity<Object> modifier(OrdreDto OrdreDto);
    ResponseEntity<Object> validation(Long[] id,String userLogin);
    ResponseEntity<Object> impressionOrdreBourse(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> jaspertReportOrdreBourse(String[] numeroOrdre, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> supprimer(Long idOrdre,String userLogin);
}
