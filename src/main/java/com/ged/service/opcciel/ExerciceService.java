package com.ged.service.opcciel;


import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.CleExercice;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface ExerciceService {
    ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters);
    List<ExerciceDto> afficherTous() throws SQLException;
    List<ExerciceDto> afficherParOPcvm(Long idOpcvm) throws SQLException;

    ResponseEntity<Object> exerciceCourant(Long idOpcvm);
    ResponseEntity<Object> exerciceParOpcvmEtCode(Long idOpcvm,String code);

    ResponseEntity<Object> exerciceEnCours(Long idOpcvm);
    ResponseEntity<Object> creerExercice(ExerciceDto exerciceDto);
    ResponseEntity<Object> modifierExercice(ExerciceDto exerciceDto);
    ResponseEntity<Object> supprimerExercice(Long idOpcvm,String codeExercice);
}
