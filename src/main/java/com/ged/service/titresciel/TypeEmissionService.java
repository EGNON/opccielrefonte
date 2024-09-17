package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmissionDto;
import com.ged.entity.titresciel.TypeEmission;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeEmissionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeEmissionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeEmission afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeEmissionDto TypeEmissionDto);
    ResponseEntity<Object> modifier(TypeEmissionDto TypeEmissionDto);
    ResponseEntity<Object> supprimer(Long id);
}
