package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SousTypeActionDto;
import com.ged.entity.titresciel.SousTypeAction;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SousTypeActionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<SousTypeActionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    SousTypeAction afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(SousTypeActionDto SousTypeActionDto);
    ResponseEntity<Object> modifier(SousTypeActionDto SousTypeActionDto);
    ResponseEntity<Object> supprimer(Long id);
}
