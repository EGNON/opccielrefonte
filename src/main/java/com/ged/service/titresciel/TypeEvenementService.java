package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEvenementDto;
import com.ged.entity.titresciel.TypeEvenement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeEvenementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeEvenementDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeEvenement afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeEvenementDto TypeEvenementDto);
    ResponseEntity<Object> modifier(TypeEvenementDto TypeEvenementDto);
    ResponseEntity<Object> supprimer(Long id);
}
