package com.ged.service.lab;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.CritereAlerteDto;
import com.ged.entity.standard.CritereAlerte;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CritereAlerteService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    List<CritereAlerteDto> afficherListe();
    ResponseEntity<Object> afficherCritereAlertes(int page, int size);
    ResponseEntity<Object> afficherCritereAlerte(Long id);
    CritereAlerte afficherCritereAlerteSelonId(Long idCritereAlerte);
    ResponseEntity<Object> creerCritereAlerte(CritereAlerteDto CritereAlerteDto);
    ResponseEntity<Object> modifierCritereAlerte(CritereAlerteDto CritereAlerteDto);
    ResponseEntity<Object> supprimerCritereAlerte(Long id);
}
