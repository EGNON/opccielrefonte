package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriquePositionDto;
import com.ged.entity.opcciel.comptabilite.CleIbRubriquePosition;
import com.ged.entity.opcciel.comptabilite.IbRubriquePosition;
import org.springframework.http.ResponseEntity;

public interface IbRubriquePositionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    IbRubriquePosition afficherSelonId(CleIbRubriquePosition idIbRubriquePosition);
    ResponseEntity<Object> afficher(CleIbRubriquePosition idIbRubriquePosition);
    ResponseEntity<Object> creer(IbRubriquePositionDto IbRubriquePositionDto);
    ResponseEntity<Object> modifier(IbRubriquePositionDto IbRubriquePositionDto);
    ResponseEntity<Object> supprimer(CleIbRubriquePosition idIbRubriquePosition);
}
