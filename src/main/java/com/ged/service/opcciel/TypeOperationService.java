package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeOperationDto;
import com.ged.entity.opcciel.comptabilite.TypeOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeOperationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeOperationDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeOperation afficherSelonId(String codeTypeOperation);
    ResponseEntity<Object> afficher(String codeTypeOperation);
    ResponseEntity<Object> creer(TypeOperationDto TypeOperationDto);
    ResponseEntity<Object> modifier(TypeOperationDto TypeOperationDto);
    ResponseEntity<Object> supprimer(String codeTypeOperation);
}
