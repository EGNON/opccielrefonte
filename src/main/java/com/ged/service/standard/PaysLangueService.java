package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.ClePaysLangue;
import com.ged.entity.standard.PaysLangue;
import org.springframework.http.ResponseEntity;

public interface PaysLangueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    PaysLangue afficherSelonId(ClePaysLangue idPaysLangue);
    ResponseEntity<Object> afficherSelonLangue(Long idLangue);
    ResponseEntity<Object> afficherSelonPays(Long idPays);
    ResponseEntity<Object> afficher(ClePaysLangue idPaysLangue);
    ResponseEntity<Object> creer(PaysLangueDto PaysLangueDto);
    ResponseEntity<Object> modifier(PaysLangueDto PaysLangueDto);
    ResponseEntity<Object> supprimer(ClePaysLangue idPaysLangue);
    ResponseEntity<Object> supprimerSelonLangue(Long idLangue);
}
