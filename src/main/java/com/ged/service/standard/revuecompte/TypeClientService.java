package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeClientDto;
import com.ged.entity.standard.revuecompte.TypeClient;
import org.springframework.http.ResponseEntity;

public interface TypeClientService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    TypeClient afficherSelonId(Long id);
    ResponseEntity<Object> creer(TypeClientDto TypeClientDto);
    ResponseEntity<Object> modifier(TypeClientDto TypeClientDto);
    ResponseEntity<Object> supprimer(Long id);
}
