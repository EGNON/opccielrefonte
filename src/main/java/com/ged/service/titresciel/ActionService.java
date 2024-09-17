package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ActionDto;
import com.ged.entity.titresciel.Action;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ActionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<ActionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Action afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(ActionDto ActionDto);
    ResponseEntity<Object> modifier(ActionDto ActionDto);
    ResponseEntity<Object> supprimer(Long id);
}
