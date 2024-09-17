package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.entity.standard.revuecompte.CategorieClient;
import org.springframework.http.ResponseEntity;

public interface CategorieClientService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    CategorieClient afficherSelonId(Long id);
    ResponseEntity<Object> creer(CategorieClientDto CategorieClientDto);
    ResponseEntity<Object> modifier(CategorieClientDto CategorieClientDto);
    ResponseEntity<Object> supprimer(Long id);
}
