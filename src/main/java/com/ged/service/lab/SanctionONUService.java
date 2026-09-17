package com.ged.service.lab;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.CritereAlerteDto;
import com.ged.dto.lab.SanctionsONUDto;
import com.ged.entity.standard.CritereAlerte;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SanctionONUService {
//    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
//    ResponseEntity<Object> afficherTous();
//    List<CritereAlerteDto> afficherListe();
//    ResponseEntity<Object> afficherCritereAlertes(int page, int size);
//    ResponseEntity<Object> afficherCritereAlerte(Long id);
//    CritereAlerte afficherCritereAlerteSelonId(Long idCritereAlerte);
    ResponseEntity<Object> listeSanctionONU(String[] sanctionsONUDtos);
}
