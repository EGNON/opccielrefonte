package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleDto;
import com.ged.entity.standard.TypeModele;
import org.springframework.http.ResponseEntity;

public interface TypeModeleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    TypeModele afficherSelonId(Long id);
    ResponseEntity<Object> creer(TypeModeleDto TypeModeleDto);
    ResponseEntity<Object> modifier(TypeModeleDto TypeModeleDto);
    ResponseEntity<Object> supprimer(Long id);
}
