
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface NatureOperationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<NatureOperationDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    NatureOperation afficherSelonId(String codeNatureOperation);
    ResponseEntity<Object> afficherSelonTypeOperation(String codeTypeOperation);
    ResponseEntity<Object> afficher(String codeNatureOperation);
    ResponseEntity<Object> creer(NatureOperationDto NatureOperationDto);
    ResponseEntity<Object> modifier(NatureOperationDto NatureOperationDto);
    ResponseEntity<Object> supprimer(String codeNatureOperation);
}
