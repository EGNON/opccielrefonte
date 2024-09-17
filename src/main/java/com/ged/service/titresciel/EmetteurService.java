package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.EmetteurDto;
import com.ged.entity.titresciel.Emetteur;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface EmetteurService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<EmetteurDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Emetteur afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(EmetteurDto EmetteurDto);
    ResponseEntity<Object> modifier(EmetteurDto EmetteurDto);
    ResponseEntity<Object> supprimer(Long id);
}
