package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationTransfertPartDto;
import com.ged.dto.request.TransfertPartAddRequest;
import com.ged.dto.request.TransfertPartListeRequest;
import com.ged.entity.opcciel.OperationTransfertPart;
import org.springframework.http.ResponseEntity;

public interface OperationTransfertPartService {
    ResponseEntity<Object> afficherTous(TransfertPartListeRequest request);
    OperationTransfertPart afficherSelonId(Long id);
    ResponseEntity<Object> creer(OperationTransfertPartDto opDto);
    ResponseEntity<Object> modifier(OperationTransfertPartDto opDto);
    ResponseEntity<Object> supprimer(Long id);

    ResponseEntity<Object> creerTransfert(TransfertPartAddRequest request);
}
