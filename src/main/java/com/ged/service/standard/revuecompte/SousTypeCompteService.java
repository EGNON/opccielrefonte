package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeCompteDto;
import com.ged.entity.standard.revuecompte.SousTypeCompte;
import org.springframework.http.ResponseEntity;

public interface SousTypeCompteService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    SousTypeCompte afficherSelonId(Long id);
    ResponseEntity<Object> creer(SousTypeCompteDto SousTypeCompteDto);
    ResponseEntity<Object> modifier(SousTypeCompteDto SousTypeCompteDto);
    ResponseEntity<Object> supprimer(Long id);
}
