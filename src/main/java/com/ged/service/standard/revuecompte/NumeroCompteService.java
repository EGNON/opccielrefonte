package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.NumeroCompteDto;
import com.ged.entity.standard.revuecompte.CleNumeroCompte;
import com.ged.entity.standard.revuecompte.NumeroCompte;
import org.springframework.http.ResponseEntity;

public interface NumeroCompteService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    NumeroCompte afficherSelonId(CleNumeroCompte idNumeroCompte);
    ResponseEntity<Object> afficher(CleNumeroCompte idNumeroCompte);
    ResponseEntity<Object> creer(NumeroCompteDto NumeroCompteDto);
    ResponseEntity<Object> modifier(NumeroCompteDto NumeroCompteDto);
    ResponseEntity<Object> supprimer(CleNumeroCompte idNumeroCompte);
}
