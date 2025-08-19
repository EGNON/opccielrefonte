package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.InfosCirculaire8Dto;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.InfosCirculaire8;
import com.ged.entity.opcciel.Ordre;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface InfosCirculaire8Service {
    ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters);
    InfosCirculaire8 afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTous(Long idOpcvm);
    ResponseEntity<Object> creer(InfosCirculaire8Dto infosCirculaire8Dto);
    ResponseEntity<Object> modifier(InfosCirculaire8Dto infosCirculaire8Dto);
    ResponseEntity<Object> supprimer(Long id,String userLogin);
}
