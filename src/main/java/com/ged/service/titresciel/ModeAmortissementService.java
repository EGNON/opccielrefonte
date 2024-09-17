package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ModeAmortissementDto;
import com.ged.entity.titresciel.ModeAmortissement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ModeAmortissementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<ModeAmortissementDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    ModeAmortissement afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(ModeAmortissementDto ModeAmortissementDto);
    ResponseEntity<Object> modifier(ModeAmortissementDto ModeAmortissementDto);
    ResponseEntity<Object> supprimer(Long id);
}
