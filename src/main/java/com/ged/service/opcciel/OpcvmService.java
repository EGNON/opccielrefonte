package com.ged.service.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.opcciel.Opcvm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpcvmService {
//    List<Object> createOpcvmFromOpcciel1() throws JsonProcessingException;
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Opcvm afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherListe();
    ResponseEntity<Object> creer(OpcvmDto opcvmDto);
    ResponseEntity<Object> modifier(OpcvmDto opcvmDto);
    ResponseEntity<Object> supprimer(Long id);
}
