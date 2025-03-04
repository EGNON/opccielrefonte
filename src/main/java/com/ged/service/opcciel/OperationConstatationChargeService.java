package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstitutionChargeAddRequest;
import com.ged.entity.opcciel.OperationTransfertPart;
import org.springframework.http.ResponseEntity;

public interface OperationConstatationChargeService {
    ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request);
    OperationTransfertPart afficherSelonId(Long id);
    ResponseEntity<Object> creer(ConstitutionChargeAddRequest request);
    ResponseEntity<Object> modifier(OperationConstatationChargeDto opDto);
    ResponseEntity<Object> supprimer(Long id);
}
