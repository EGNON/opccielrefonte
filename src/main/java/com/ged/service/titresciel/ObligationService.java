package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ObligationDto;
import com.ged.entity.titresciel.Obligation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ObligationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<ObligationDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Obligation afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(ObligationDto ObligationDto);
    ResponseEntity<Object> modifier(ObligationDto ObligationDto);
    ResponseEntity<Object> supprimer(Long id);
}
