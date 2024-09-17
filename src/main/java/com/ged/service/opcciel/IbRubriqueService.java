package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriqueDto;
import com.ged.entity.opcciel.comptabilite.CleIbRubrique;
import com.ged.entity.opcciel.comptabilite.IbRubrique;
import org.springframework.http.ResponseEntity;

public interface IbRubriqueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    IbRubrique afficherSelonId(CleIbRubrique idIbRubrique);
    ResponseEntity<Object> afficher(CleIbRubrique idIbRubrique);
    ResponseEntity<Object> creer(IbRubriqueDto IbRubriqueDto);
    ResponseEntity<Object> modifier(IbRubriqueDto IbRubriqueDto);
    ResponseEntity<Object> supprimer(CleIbRubrique idIbRubrique);
}
