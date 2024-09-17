package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CorrespondanceDto;
import com.ged.entity.opcciel.comptabilite.CleCorrespondance;
import com.ged.entity.opcciel.comptabilite.Correspondance;
import org.springframework.http.ResponseEntity;

public interface CorrespondanceService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    Correspondance afficherSelonId(CleCorrespondance idCorrespondance);
    ResponseEntity<Object> afficher(CleCorrespondance idCorrespondance);
    ResponseEntity<Object> creer(CorrespondanceDto correspondanceDto);
    ResponseEntity<Object> modifier(CorrespondanceDto correspondanceDto);
    ResponseEntity<Object> supprimer(CleCorrespondance idCorrespondance);
}
