package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureEvenementDto;
import com.ged.entity.titresciel.NatureEvenement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface NatureEvenementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<NatureEvenementDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    NatureEvenement afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(NatureEvenementDto NatureEvenementDto);
    ResponseEntity<Object> modifier(NatureEvenementDto NatureEvenementDto);
    ResponseEntity<Object> supprimer(Long id);
}
