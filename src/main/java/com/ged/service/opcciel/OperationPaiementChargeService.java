package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationPaiementChargeDto;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationPaiementCharge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationPaiementChargeService {
    ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request);
    OperationPaiementCharge afficherSelonId(Long id);
    ResponseEntity<?> creer(Long [] request,ConstatationChargeListeRequest listeRequest);
    ResponseEntity<?> modifier(ConstatationChargeEditRequest request);
    ResponseEntity<?> supprimer(Long id);
}
