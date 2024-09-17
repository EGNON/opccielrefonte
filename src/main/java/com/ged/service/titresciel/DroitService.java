package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DroitDto;
import com.ged.entity.titresciel.Droit;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DroitService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<DroitDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Droit afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(DroitDto DroitDto);
    ResponseEntity<Object> modifier(DroitDto DroitDto);
    ResponseEntity<Object> supprimer(Long id);
}
