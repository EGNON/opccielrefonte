package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationRegulEcartSoldeDto;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationPaiementCharge;
import com.ged.entity.opcciel.OperationRegulEcartSolde;
import jakarta.persistence.ParameterMode;
import org.springframework.http.ResponseEntity;

public interface OperationRegulEcartSoldeService {
    ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request);
    OperationRegulEcartSolde afficherSelonId(Long id);
    ResponseEntity<?>  afficher(Long id);
    ResponseEntity<?> creer(OperationRegulEcartSoldeDto operationRegulEcartSolde);
    ResponseEntity<?> modifier(OperationRegulEcartSoldeDto operationRegulEcartSolde);
    ResponseEntity<?> supprimer(String userLogin,Long idOperation);
}
