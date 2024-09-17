package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypePositionDto;
import com.ged.entity.opcciel.comptabilite.TypePosition;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypePositionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypePositionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypePosition afficherSelonId(String codeTypePosition);
    ResponseEntity<Object> afficher(String codeTypePosition);
    ResponseEntity<Object> creer(TypePositionDto TypePositionDto);
    ResponseEntity<Object> modifier(TypePositionDto TypePositionDto);
    ResponseEntity<Object> supprimer(String codeTypePosition);
}
