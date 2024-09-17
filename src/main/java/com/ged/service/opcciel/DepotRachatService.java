
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DepotRachatService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<DepotRachatDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    DepotRachat afficherSelonId(Long IdOperation);
    ResponseEntity<Object> afficher(Long IdOperation);
    ResponseEntity<Object> creer(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> supprimer(Long IdOperation);
}
