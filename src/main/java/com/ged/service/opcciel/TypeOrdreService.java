package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.TypeOrdreDto;
import com.ged.entity.opcciel.TypeOrdre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeOrdreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeOrdreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeOrdre afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeOrdreDto TypeOrdreDto);
    ResponseEntity<Object> modifier(TypeOrdreDto TypeOrdreDto);
    ResponseEntity<Object> supprimer(Long id);
}
