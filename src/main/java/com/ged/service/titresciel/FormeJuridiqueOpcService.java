package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.FormeJuridiqueOpcDto;
import com.ged.entity.titresciel.FormeJuridiqueOpc;
import org.springframework.http.ResponseEntity;

public interface FormeJuridiqueOpcService {
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherParPage(int page, int size);
    FormeJuridiqueOpc afficherSelonId(Long id);
    ResponseEntity<Object> creer(FormeJuridiqueOpcDto formeJuridiqueOpcDto);
    ResponseEntity<Object> modifier(FormeJuridiqueOpcDto formeJuridiqueOpcDto);
    ResponseEntity<Object> supprimer(Long id);
}
