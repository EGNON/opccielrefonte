package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import org.springframework.http.ResponseEntity;

public interface OperationConstatationChargeService {
    ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request);
    OperationConstatationCharge afficherSelonId(Long id);
    ResponseEntity<?> creer(ConstatationChargeAddRequest request);
    ResponseEntity<?> modifier(ConstatationChargeEditRequest request);
    ResponseEntity<?> supprimer(Long id);
}
