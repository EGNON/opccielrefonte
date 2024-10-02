package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureNatureOperationDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureNatureOperation;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureNatureOperation;
import org.springframework.http.ResponseEntity;

public interface ModeleEcritureNatureOperationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ModeleEcritureNatureOperation afficherSelonId(CleModeleEcritureNatureOperation idModeleEcritureNatureOperation);
    ResponseEntity<Object> afficher(CleModeleEcritureNatureOperation idModeleEcritureNatureOperation);
    ResponseEntity<Object> creer(ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto);
    ResponseEntity<Object> modifier(ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto);
    ResponseEntity<Object> supprimer(CleModeleEcritureNatureOperation idModeleEcritureNatureOperation);
}
