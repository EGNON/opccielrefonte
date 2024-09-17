package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.HistoriqueNumeroCompteDto;
import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import org.springframework.http.ResponseEntity;

public interface HistoriqueNumeroCompteService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    HistoriqueNumeroCompte afficherSelonId(Long id);
    ResponseEntity<Object> creer(HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto);
    ResponseEntity<Object> modifier(HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto);
    ResponseEntity<Object> supprimer(Long id);
}
