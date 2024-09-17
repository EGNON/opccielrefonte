package com.ged.service.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeClientDto;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import org.springframework.http.ResponseEntity;

public interface SousTypeClientService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficherPersonnePhysique();
    ResponseEntity<Object> afficherAutresTypePersonne();
    ResponseEntity<Object> afficher(Long id);
    SousTypeClient afficherSelonId(Long id);
    ResponseEntity<Object> creer(SousTypeClientDto SousTypeClientDto);
    ResponseEntity<Object> modifier(SousTypeClientDto SousTypeClientDto);
    ResponseEntity<Object> supprimer(Long id);
}
