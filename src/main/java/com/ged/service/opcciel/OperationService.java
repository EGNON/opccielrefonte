package com.ged.service.opcciel;

import com.ged.dto.request.ConsultationEcritureRequest;
import org.springframework.http.ResponseEntity;

public interface OperationService {
    ResponseEntity<Object> afficherTous(ConsultationEcritureRequest request);
}
