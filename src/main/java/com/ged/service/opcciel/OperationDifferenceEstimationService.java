package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface OperationDifferenceEstimationService {
    ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request);
    OperationDifferenceEstimation afficherSelonId(CleOperationDifferenceEstimation id);
    ResponseEntity<Object> afficher(CleOperationDifferenceEstimation id);
    ResponseEntity<Object> afficherTitre(Long idOpcvm,LocalDateTime dateEstimation,String typeEvenement);
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> creer(OperationDifferenceEstimationDto operationDifferenceEstimationDto);
    ResponseEntity<Object> modifier(OperationDifferenceEstimationDto operationDifferenceEstimationDto);
    ResponseEntity<Object> supprimer(String userLogin,
                                    Long idDifferenceEstimation);
}
