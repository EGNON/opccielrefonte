package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.LangueDto;
import com.ged.entity.standard.Langue;
import org.springframework.http.ResponseEntity;

public interface LangueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);

    ResponseEntity<Object> afficher(Long id);
    Langue afficherSelonId(Long id);
    ResponseEntity<Object> creer(LangueDto LangueDto);
    ResponseEntity<Object> modifier(LangueDto LangueDto);
    ResponseEntity<Object> supprimer(Long id);
}
