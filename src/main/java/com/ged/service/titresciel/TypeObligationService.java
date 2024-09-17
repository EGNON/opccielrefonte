package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeObligationDto;
import com.ged.entity.titresciel.TypeObligation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeObligationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeObligationDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeObligation afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeObligationDto TypeObligationDto);
    ResponseEntity<Object> modifier(TypeObligationDto TypeObligationDto);
    ResponseEntity<Object> supprimer(Long id);
}
