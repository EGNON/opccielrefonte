package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import org.springframework.http.ResponseEntity;

public interface OperationSouscriptionRachatService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    OperationSouscriptionRachat afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> creer(OperationSouscriptionRachatDto operationSouscriptionRachatDto);
    ResponseEntity<Object> creer(OperationSouscriptionRachatDto2[] operationSouscriptionRachatTab);
    ResponseEntity<Object> modifier(OperationSouscriptionRachatDto operationSouscriptionRachatDto);
    ResponseEntity<Object> supprimer(Long id);
}
