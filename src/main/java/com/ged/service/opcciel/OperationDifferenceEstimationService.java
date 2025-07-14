package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationDifferenceEstimationService {
    ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request);
    OperationDifferenceEstimation afficherSelonId(CleOperationDifferenceEstimation id);
    ResponseEntity<Object> afficher(CleOperationDifferenceEstimation id);
    ResponseEntity<Object> afficherTitre(Long idOpcvm,LocalDateTime dateEstimation,String typeEvenement);
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> creer(OperationDifferenceEstimationDto operationDifferenceEstimationDto);
    ResponseEntity<Object> enregistrerDifferenceEstimation(DifferenceEstimationRequest  request);
    ResponseEntity<Object> modifier(OperationDifferenceEstimationDto operationDifferenceEstimationDto);
     ResponseEntity<Object> genrerDifferenceEstimation(Long idOpcvm, Boolean estEnCloture);
     ResponseEntity<Object> precalculDifferenceEstimation(DifferenceEstimationRequest differenceEstimationRequest);
     ResponseEntity<Object> validationNiveau(DifferenceEstimationRequest differenceEstimationRequest);
    ResponseEntity<Object> supprimer(String userLogin,
                                    Long idDifferenceEstimation);
}
