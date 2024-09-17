package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeGarantDto;
import com.ged.entity.titresciel.TypeGarant;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeGarantService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeGarantDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeGarant afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeGarantDto TypeGarantDto);
    ResponseEntity<Object> modifier(TypeGarantDto TypeGarantDto);
    ResponseEntity<Object> supprimer(Long id);
}
