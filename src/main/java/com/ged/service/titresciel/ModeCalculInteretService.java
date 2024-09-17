package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ModeCalculInteretDto;
import com.ged.entity.titresciel.ModeCalculInteret;
import org.springframework.http.ResponseEntity;

public interface ModeCalculInteretService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ModeCalculInteret afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(ModeCalculInteretDto modeCalculInteretDto);
    ResponseEntity<Object> modifier(ModeCalculInteretDto modeCalculInteretDto);
    ResponseEntity<Object> supprimer(Long id);
}
