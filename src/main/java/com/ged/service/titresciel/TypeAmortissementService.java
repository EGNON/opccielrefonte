package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.entity.titresciel.TypeAmortissement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeAmortissementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeAmortissementDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeAmortissement afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeAmortissementDto TypeAmortissementDto);
    ResponseEntity<Object> modifier(TypeAmortissementDto TypeAmortissementDto);
    ResponseEntity<Object> supprimer(Long id);
}
