
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.projection.NbrePartProjection;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface DepotRachatService {
    ResponseEntity<Object> afficherTousLesDepots(DatatableParameters parameters, Long idOpcvm, Long idSeance);

    ResponseEntity<Object> afficherTous(DatatableParameters parameters, long idOpcvm, long idSeance, String codeNatureOperation);
    Page<DepotRachatDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    List<NbrePartProjection> afficherNbrePart(Long idOpcvm, Long idActionnaire);
    DepotRachat afficherSelonId(Long IdOperation);
    ResponseEntity<Object> afficher(Long IdOperation);
    ResponseEntity<Object> creer(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> supprimer(Long IdOperation);
}
