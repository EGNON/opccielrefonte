package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeActionDto;
import com.ged.entity.titresciel.TypeAction;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeActionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeActionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeAction afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeActionDto TypeActionDto);
    ResponseEntity<Object> modifier(TypeActionDto TypeActionDto);
    ResponseEntity<Object> supprimer(Long id);
}
