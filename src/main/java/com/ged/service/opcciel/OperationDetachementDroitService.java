package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDetachementDroitDto;
import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.projection.OperationDetachementDroitProjection;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface OperationDetachementDroitService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTitre();
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idTitre);
    ResponseEntity<Object> creer(OperationDetachementDroitDto operationDetachementDto);
    ResponseEntity<Object> modifier(OperationDetachementDroitDto operationDetachementDto);
    ResponseEntity<Object> supprimer(String userLogin,
                                    Long idDetachement);
}
