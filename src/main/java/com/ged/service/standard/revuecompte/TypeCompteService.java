package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeCompteDto;
import com.ged.entity.standard.revuecompte.TypeCompte;
import org.springframework.http.ResponseEntity;

public interface TypeCompteService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    TypeCompte afficherSelonId(Long id);
    ResponseEntity<Object> creer(TypeCompteDto TypeCompteDto);
    ResponseEntity<Object> modifier(TypeCompteDto TypeCompteDto);
    ResponseEntity<Object> supprimer(Long id);
}
