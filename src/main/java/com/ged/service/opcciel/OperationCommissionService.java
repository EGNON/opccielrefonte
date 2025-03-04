package com.ged.service.opcciel;

import com.ged.dto.request.*;
import com.ged.entity.opcciel.OperationConstatationCharge;
import org.springframework.http.ResponseEntity;

public interface OperationCommissionService {
    ResponseEntity<?> afficherTous(CommissionListeRequest request);
    OperationConstatationCharge afficherSelonId(Long id);
    ResponseEntity<?> creer(CommissionAddRequest request);
    ResponseEntity<?> modifier(CommissionEditRequest request);
    ResponseEntity<?> supprimer(Long id);
}
